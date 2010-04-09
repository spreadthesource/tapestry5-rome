//
// Copyright 2010 Robin Komiwes, Bruno Verachten, Christophe Cordenier
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// 	http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package com.spreadthesource.tapestry.rome.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.tapestry5.ContentType;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;

public class FeedResultProcessor<T extends WireFeed> implements ComponentEventResultProcessor<T>
{

    private final Request request;

    private final Response response;

    private final String outputEncoding;

    public FeedResultProcessor(Request request, Response response,
            @Inject @Symbol(SymbolConstants.CHARSET) String outputEncoding)
    {
        this.response = response;
        this.request = request;
        this.outputEncoding = outputEncoding;
    }

    public void processResultValue(T value) throws IOException
    {

        if (value == null) { return; }

        request.setAttribute(InternalConstants.SUPPRESS_COMPRESSION, true);

        ContentType contentType = null;

        if (value instanceof Feed)
        {
            contentType = new ContentType("application/atom+xml",
                    value.getEncoding() == null ? outputEncoding : value.getEncoding());
        }

        else if (value instanceof Channel)
        {
            contentType = new ContentType("application/rss+xml",
                    value.getEncoding() == null ? outputEncoding : value.getEncoding());
        }
        else
        {
            contentType = new ContentType("text/xml", value.getEncoding() == null ? outputEncoding
                    : value.getEncoding());
        }

        WireFeedOutput wireFeedOutput = new WireFeedOutput();

        PrintWriter pw = response.getPrintWriter(contentType.toString());

        try
        {
            wireFeedOutput.output(value, pw);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Error during feed generation",e);
        }
        catch (FeedException e)
        {
            throw new RuntimeException("Error during feed generation",e);
        }

        pw.flush();
    }
}
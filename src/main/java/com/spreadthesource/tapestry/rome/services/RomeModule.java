package com.spreadthesource.tapestry.rome.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.services.ComponentEventResultProcessor;

import com.sun.syndication.feed.atom.Feed;

public class RomeModule
{
    /**
     * Allow to return Rome feeds
     * @param configuration
     */
    public void contributeComponentEventResultProcessor(MappedConfiguration<Class, ComponentEventResultProcessor> configuration)
    {
        configuration.addInstance(Feed.class, FeedResultProcessor.class);
    }
}

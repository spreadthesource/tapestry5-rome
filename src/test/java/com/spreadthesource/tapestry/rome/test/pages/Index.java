//
// Copyright 2009 Robin Komiwes, Bruno Verachten, Christophe Cordenier
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

package com.spreadthesource.tapestry.rome.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.OnEvent;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

public class Index
{

    @OnEvent(value = "atom")
    Feed atom()
    {
        Feed feed = new Feed("atom_1.0");

        feed.setTitle("my Atom feed");

        List<Person> authors = new ArrayList<Person>();

        List<Entry> entries = new ArrayList<Entry>();

        Entry e = new Entry();

        Person person = new Person();
        person.setEmail("http://twitter.com/robinkomiwes");
        person.setName("Robin K");

        e.setTitle("My first title");
        e.setAuthors(authors);
        entries.add(e);
        feed.setEntries(entries);

        return feed;
    }

    @OnEvent(value = "rss")
    Channel rss()
    {
        Channel feed = new Channel("rss_2.0");

        feed.setTitle("my RSS feed");
        feed.setDescription("Simple test case...");
        feed.setLink("http://spreadthesource.com");

        Item i = new Item();
        i.setTitle("My first title");
        i.setAuthor("Robin K");
        i.setUri("http://spreadthesource.com");

        List<Item> items = new ArrayList<Item>();
        items.add(i);

        feed.setItems(items);

        return feed;
    }
}

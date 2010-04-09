# Tapestry 5 Rome Plugin

## How to

Channel and Feed objects from Rome are now an allowed return type for action methods.

Example:

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

## Maven dependency

To use this plugin, add the following dependency in your `pom.xml`.

	<dependencies>
		...
		<dependency>
			<groupId>com.spreadthesource</groupId>
			<artifactId>tapestry5-rome</artifactId>
			<version>1.1</version>
		</dependency>
		...
	</dependencies>
	
	<repositories>
		...
		<repository>
			<id>devlab722-repo</id>
			<url>http://nexus.devlab722.net/nexus/content/repositories/releases
			</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>

		<repository>
			<id>devlab722-snapshot-repo</id>
			<url>http://nexus.devlab722.net/nexus/content/repositories/snapshots
			</url>
			<releases>
				<enabled>false</enabled>
			</releases>
		</repository>
		
		...
	</repositories>

## More Informations & contacts

* Blog: http://spreadthesource.com
* Twitter: http://twitter.com/spreadthesource


## License

This project is distributed under Apache 2 License. See LICENSE.txt for more information.


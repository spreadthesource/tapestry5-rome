package com.spreadthesource.tapestry.rome.integration;


import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class RomeTest extends SeleniumTestCase
{   
    @Test
    public void atom()
    {
        open("/index:atom");
        
        assertTrue(getBodyText().contains("my Atom feed"));
    }
    
    @Test
    public void rss()
    {
        open("/index:rss");
        assertTrue(getBodyText().contains("my RSS feed"));

    }
}

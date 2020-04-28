package org.example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testConnection()
    {
        RedissonClient jedis;
        jedis = App.connection();
        assertFalse( jedis.isShutdown() );
    }
    @Test
    public void testImage()
    {
        RedissonClient jedis;
        jedis = App.connection();
        RMap<String, String> map = jedis.getMap("simpleMap");
        map.put("CleTest-image", "testValue");
        assertEquals("testValue" ,App.getImage("CleTest"));
    }
    @Test
    public void testTitle()
    {
        App.connection();
        assertEquals("testValue" ,App.getTitre("CleTest"));
    }
    @Test
    public void testExplanation()
    {
        App.connection();
        assertEquals("testValue" ,App.getTitre("CleTest"));
    }
}

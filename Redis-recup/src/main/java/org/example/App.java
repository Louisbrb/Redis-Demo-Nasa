package org.example;
import org.redisson.Redisson;

import org.redisson.api.RBucket;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Hello world!
 *
 */
public class App 
{

    private static RedissonClient jedis;
    public static void main( String[] args )
    {

        jedis = connection();
        getImage(args[0]);
        getTitre(args[0]);
        getExplanation(args[0]);

    }
    public static RedissonClient connection()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //Connecting to Redis server on localhost
        RedissonClient jedis =  Redisson.create(config);
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        return jedis;
    }
    public static String getImage(String date)
    {
        RMap<String, String> map = jedis.getLocalCachedMap("simpleMap");
        String mapValue = map.get(date + "-image");
        System.out.println(mapValue);
        return mapValue;

    }
    public static String getTitre(String date)
    {
        RMap<String, String> map = jedis.getLocalCachedMap("anyMap");
        String mapValue = map.get(date + "-title");
        System.out.println(mapValue);
        return mapValue;
    }
    public static String getExplanation(String date)
    {
        RMap<String, String> map = jedis.getLocalCachedMap(date + "-explanation");
        String mapValue = map.get(date + "-explanation");
        System.out.println(mapValue);
        return mapValue;

    }
}

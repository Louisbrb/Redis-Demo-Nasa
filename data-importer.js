const redis = require('redis');
const https = require('https');
const cle= 'foWQgj4ejPygZ020vUAtyTJdowRs9FdkGBJGWYR5';
var client = redis.createClient();


client.on('connect', function() {
    console.log('Connexion a reddit réussi');
});
client.on('error', function (err) {
    console.log('Erreur lors de la connexion a redis : ' + err);
});

function setUrl() {
	var commonURI = "https://api.nasa.gov/planetary/apod?api_key="+ cle;
	return commonURI;
}
https.get(setUrl(), (resp) => {
    let data = '';
  
    // A chunk of data has been recieved.
    resp.on('data', (chunk) => {
      data += chunk;
    });
    resp.on('end', () => {
        client.set(JSON.parse(data).date + "-explanation", JSON.parse(data).explanation, redis.print);
        client.set(JSON.parse(data).date + "-title", JSON.parse(data).title, redis.print);
        client.set(JSON.parse(data).date + "-image", JSON.parse(data).hdurl, redis.print);
        // valeur pour les cas de test
        client.set("CleTest" + "-image", "testValue", redis.print);
        client.set("CleTest" + "-title", "testValue", redis.print);
        client.set("CleTest" + "-explanation", "testValue", redis.print);
        console.log(JSON.parse(data).date);
      });
    
    }).on("error", (err) => {
      console.log("Error: " + err.message);
    });  
(async () => {
    try {
        
        const redisResult = await redisTest();

        console.log(`Redis execution time:  ${new Date() - start} ms`);
        console.log(`name: ${redisResult.name}, address: ${redisResult.address}`);
    } catch (error) {
        console.log(error);
    }
});
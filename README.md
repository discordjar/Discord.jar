# Discord.jar

A Robust API and Websocket wrapper for Discord

---

### Setting Up
```java
public class Main {

    private final String TOKEN = "YOUR_SECRET_BOT_TOKEN";
    
    public static void main(String[] args) {
        
        DJAR djar = new DJARManager(TOKEN).buildClient();
    }

}
```
using Sharding
```java
public class MainSharding {
    private final String TOKEN = "BOT_TOKEN_HERE";
    
    public static void main(String[] args) {
        DJARManager manager = new DJARManager(TOKEN);
        
        for (int i = 0; i < 3; i++) {
            manager.buildShardedClient(i, 3);
        }
    }
}
```

---


### License
Discord.jar is licensed under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0) for the full license visit [here](https://github.com/discordjar/Discord.jar/blob/master/LICENSE)

---
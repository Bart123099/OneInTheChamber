package nl.bart123099.oneinthechamber.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import nl.bart123099.oneinthechamber.OneInTheChamber;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MongoDataHandler implements DataHandler {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> playerDataCollection;

    public MongoDataHandler() {
        this.mongoClient = MongoClients.create("mongodb+srv://bart123099:test123@oneinthechambercluster.0zskd.mongodb.net/bootcamp?retryWrites=true&w=majority");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("bootcamp");
        this.playerDataCollection = mongoDatabase.getCollection("players");
    }

    @Override
    public CompletableFuture<PlayerData> getData(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {

            Player player = Bukkit.getPlayer(uuid);

            Document doc = playerDataCollection.find(Filters.eq("_id", uuid.toString())).first();
            if(doc == null) {
                PlayerData playerData = new PlayerData(uuid, player.getName(), 0, 0);
                OneInTheChamber.onlinePlayers.put(player.getUniqueId(), playerData);
                return playerData;
            }

            String username = player.getName();
            int kills = doc.getInteger("kills");
            int deaths = doc.getInteger("deaths");

            PlayerData playerData = new PlayerData(uuid, username, kills, deaths);
            return playerData;
        });
    }

    @Override
    public void saveData(PlayerData playerData) {

        CompletableFuture.runAsync(() -> {
            Document doc = new Document();
            doc.put("_id", playerData.getUUID().toString());
            doc.put("username", playerData.getUserName());
            doc.put("kills", playerData.getKills());
            doc.put("deaths", playerData.getDeaths());

            playerDataCollection.replaceOne(Filters.eq("_id", playerData.getUUID().toString()), doc, new ReplaceOptions().upsert(true));
        });

    }
}

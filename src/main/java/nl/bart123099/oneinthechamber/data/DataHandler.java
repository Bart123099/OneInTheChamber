package nl.bart123099.oneinthechamber.data;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DataHandler {

    CompletableFuture<PlayerData> getData(UUID uuid);

    void saveData(PlayerData playerData);


}

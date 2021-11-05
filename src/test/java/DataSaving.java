import me.playbosswar.playapi.arena.Arena;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class DataSaving {
    @Test
    @DisplayName("Convert class to yaml")
    void convertClassToYaml() {
        try {
            YamlConfiguration config = new YamlConfiguration();
            File existingConfig = new File("src/test/resources/arenas.yaml");
            config.load(existingConfig);
            List<Arena> def = new ArrayList<>();
            List<Arena> existingData = (List<Arena>) config.getList("arenas", def);
            System.out.println(existingData);
            System.out.println(config.get("arenas"));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Get class from yaml")
    void getClassFromYaml() {
        try {
            YamlConfiguration config = new YamlConfiguration();
            File existingConfig = new File("src/test/resources/arenas.yaml");
            config.load(existingConfig);
            Arena arena = config.getObject("arenas.1234", Arena.class);
            System.out.println(arena);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

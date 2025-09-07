package com.franzy.norain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import btw.BTWAddon;

public class NoRainAddon extends BTWAddon {

    public static int disableRainTicks = 240000;

    public NoRainAddon() {
    }

    @Override
    public void initialize() {
        loadConfig();
        System.out.println("[No Rain Addon] Initialized! disableRainTicks = " + disableRainTicks);
    }

    private void loadConfig() {
        try {
            File configDir = new File("config");
            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            File configFile = new File(configDir, "norain.cfg");
            Properties props = new Properties();

            if (configFile.exists()) {
                FileInputStream in = new FileInputStream(configFile);
                props.load(in);
                in.close();

                String ticksStr = props.getProperty("disableRainTicks", "240000");
                disableRainTicks = Integer.parseInt(ticksStr);
            } else {
                props.setProperty("disableRainTicks", String.valueOf(disableRainTicks));
                FileWriter writer = new FileWriter(configFile);
                props.store(writer, "NoRainAddon Configuration");
                writer.close();
            }

        } catch (IOException e) {
            System.err.println("[No Rain Addon] Failed to load config: " + e.getMessage());
        }
    }
}




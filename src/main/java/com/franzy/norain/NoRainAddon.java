package com.franzy.norain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import api.BTWAddon;

public class NoRainAddon extends BTWAddon {

    public static int disableRainTicks = 240000;

    public static boolean disableRainForever = true;

    @Override
    public void initialize() {

        loadConfig();

        System.out.println(
                "[No Rain Addon] Initialized!"
        );

        System.out.println(
                "disableRainTicks = " + disableRainTicks
        );

        System.out.println(
                "disableRainForever = " + disableRainForever
        );
    }

    private void loadConfig() {

        try {

            File configDir = new File("config");

            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            File configFile =
                    new File(configDir, "norain.conf");

            Properties props = new Properties();

            if (configFile.exists()) {

                FileInputStream in =
                        new FileInputStream(configFile);

                props.load(in);

                in.close();

                String ticksStr =
                        props.getProperty(
                                "disableRainTicks",
                                "240000"
                        );

                disableRainTicks =
                        Integer.parseInt(ticksStr);

                String foreverStr =
                        props.getProperty(
                                "disableRainForever",
                                "true"
                        );

                disableRainForever =
                        Boolean.parseBoolean(foreverStr);

            } else {

                props.setProperty(
                        "disableRainTicks",
                        String.valueOf(disableRainTicks)
                );

                props.setProperty(
                        "disableRainForever",
                        String.valueOf(disableRainForever)
                );

                FileWriter writer =
                        new FileWriter(configFile);

                props.store(
                        writer,
                        "NoRainAddon Config"
                );

                writer.close();
            }

        } catch (IOException e) {

            System.err.println(
                    "[No Rain Addon] Failed to load config: "
                            + e.getMessage()
            );
        }
    }
}




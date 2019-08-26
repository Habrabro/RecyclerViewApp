package com.example.recyclerviewapp;

public class App
{
    private String name, pack, version, codeVersion;
    private int image;

    public String getName() { return name; }
    public String getPack() { return pack; }
    public String getVersion() { return version; }
    public String getCodeVersion() { return codeVersion; }
    public int getImage() { return image; }

    public App(String name, String pack, String version, String codeVersion, int image)
    {
        this.name = name;
        this.pack = pack;
        this.version = version;
        this.codeVersion = codeVersion;
        this.image = image;
    }
}

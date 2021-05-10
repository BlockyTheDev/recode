package io.github.codeutilities.config.structure;

import io.github.codeutilities.commands.sys.IManager;

import java.util.ArrayList;
import java.util.List;

public class ConfigSubGroup implements IManager<ConfigSetting<?>> {
    private final List<ConfigSetting<?>> settings = new ArrayList<>();
    private boolean startExpanded = true;
    private final String name;

    public ConfigSubGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartExpanded(boolean startExpanded) {
        this.startExpanded = startExpanded;
    }

    public boolean isStartExpanded() {
        return startExpanded;
    }

    @Deprecated
    @Override
    public void initialize() {/*not needed*/}

    @Override
    public void register(ConfigSetting<?> object) {
        this.settings.add(object);
    }

    @Override
    public List<ConfigSetting<?>> getRegistered() {
        return settings;
    }
}
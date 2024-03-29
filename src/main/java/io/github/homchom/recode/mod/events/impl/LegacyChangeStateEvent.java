package io.github.homchom.recode.mod.events.impl;

import io.github.homchom.recode.event.RecodeEvents;
import io.github.homchom.recode.mod.features.StateOverlayHandler;
import io.github.homchom.recode.mod.features.discordrpc.DFDiscordRPC;
import io.github.homchom.recode.mod.features.streamer.StreamerModeHandler;
import io.github.homchom.recode.sys.networking.DFState;
import io.github.homchom.recode.sys.player.chat.MessageGrabber;

public class LegacyChangeStateEvent {
    public LegacyChangeStateEvent() {
        RecodeEvents.CHANGE_DF_STATE.listen(context -> run(context.getNew(), context.getOld()));
    }

    private void run(DFState newState, DFState oldState) {
        StreamerModeHandler.handleStateChange(oldState, newState);

        if (newState.mode == DFState.Mode.OFFLINE) {
            MessageGrabber.reset();
        }

        try {
            DFDiscordRPC.getInstance().update(newState);
            StateOverlayHandler.setState(newState);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

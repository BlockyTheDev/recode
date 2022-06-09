package io.github.homchom.recode.sys.player;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.homchom.recode.Recode;
import io.github.homchom.recode.event.RecodeEvents;
import io.github.homchom.recode.sys.networking.DFState;
import net.minecraft.world.phys.Vec3;

public class DFInfo {

    public static final String IP = "mcdiamondfire.com";
    public static String patchId = "5.3";
    public static DFState.CurrentState currentState = new DFState.CurrentState();
    public static boolean isInBeta = false;
    public static Vec3 plotCorner = null;

    public static boolean isPatchNewer(String base, String target) {
        String[] baseSplit = base.split("\\.", 0);
        String[] targetSplit = target.split("\\.", 0);

        boolean oldNumberFound = false;

        int l = baseSplit.length;
        if (targetSplit.length > baseSplit.length) l = targetSplit.length;

        for (int i = 0; i < l; i++) {
            String currentBase = "0";
            String currentTarget = "0";

            if (baseSplit.length > i) currentBase = baseSplit[i];
            if (targetSplit.length > i) currentTarget = targetSplit[i];

            if (Integer.parseInt(currentBase) > Integer.parseInt(currentTarget)) {
                return true;
            } else {
                if (Integer.parseInt(currentBase) < Integer.parseInt(currentTarget)) {
                    oldNumberFound = true;
                }
            }
        }
        return !oldNumberFound;
    }

    public static boolean isOnDF() {
        if (Recode.MC.getCurrentServer() == null) return false;
        return Recode.MC.getCurrentServer().ip.contains("mcdiamondfire.com");
    }

    public static void setCurrentState(DFState state) {
        DFState.CurrentState newState = new DFState.CurrentState(state);
        if (!currentState.equals(newState)) {
            RecodeEvents.CHANGE_DF_STATE.invoke(new RecodeEvents.StateChange(newState, currentState));
        }
        currentState = newState;
    }
}

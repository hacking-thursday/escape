package tw.h4.game.escape.disaster;

import android.content.Context;

public class DisasterEngine {
    public enum Type {
        NUCLEAR_DISASTER(NuclearDisaster.class), ERATHQUAKE(Earthquake.class), TSUNAMI(
                Tsunami.class), VOLCAIC_ERUPTIONS(VolcanicEruptions.class);
        private Class<? extends Disaster> kls;

        private Type(Class<? extends Disaster> klass) {
            kls = klass;
        }

        public Class<? extends Disaster> getTypeClass() {
            return kls;
        }
    }

    private Context mCtx;

    public DisasterEngine(Context context) {
        mCtx = context;
    }

    public Disaster getDisaster(int type) {
        Type[] types = Type.values();
        Class<? extends Disaster> kls;
        if (types.length < type) {
            kls = types[0].getTypeClass();
        } else {
            kls = types[type].getTypeClass();
        }
        Disaster obj = null;
        try {
            obj = kls.newInstance();
            // TODO: set source here
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (null == obj) {
            obj = new NuclearDisaster();
        }
        return obj;
    }
}

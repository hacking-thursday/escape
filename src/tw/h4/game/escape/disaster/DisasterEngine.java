package tw.h4.game.escape.disaster;

import android.content.Context;

public class DisasterEngine {
	public enum Type {
		NUCLEAR_DISASTER(NuclearDisaster.class), ERATHQUAKE(Earthquake.class), TSUNAMI(
		        Tsunami.class), VOLCAIC_ERUPTIONS(VolcanicEruptions.class);
		private Class<? extends Object> kls;

		private Type(Class<? extends Object> klass) {
			kls = klass;
		}

		public Class<? extends Object> getTypeClass() {
			return kls;
		}
	}

	private Context mCtx;

	public DisasterEngine(Context context) {
		mCtx = context;
	}

	public Disaster getDisaster(int type) {
		Type[] types = Type.values();
		Class<? extends Object> kls;
		if (types.length < type) {
			kls = types[0].getClass();
		} else {
			kls = types[type].getClass();
		}
		Disaster obj = null;
		try {
			obj = (Disaster) kls.newInstance();
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

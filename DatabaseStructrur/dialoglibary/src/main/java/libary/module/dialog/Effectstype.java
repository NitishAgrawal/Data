package libary.module.dialog;

import libary.module.dialog.effects.BaseEffects;
import libary.module.dialog.effects.FadeIn;
import libary.module.dialog.effects.FlipH;
import libary.module.dialog.effects.FlipV;
import libary.module.dialog.effects.NewsPaper;
import libary.module.dialog.effects.SideFall;
import libary.module.dialog.effects.SlideLeft;
import libary.module.dialog.effects.SlideRight;
import libary.module.dialog.effects.SlideTop;

public enum  Effectstype {

    Fadein(FadeIn.class),
   // FadeOut(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(libary.module.dialog.effects.SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(libary.module.dialog.effects.Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(libary.module.dialog.effects.RotateBottom.class),
    RotateLeft(libary.module.dialog.effects.RotateLeft.class),
    Slit(libary.module.dialog.effects.Slit.class),
    Shake(libary.module.dialog.effects.Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}

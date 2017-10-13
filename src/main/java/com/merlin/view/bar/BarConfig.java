package com.merlin.view.bar;

import com.merlin.core.util.MUtil;
import com.merlin.view.bar.model.Bar;
import com.merlin.view.bar.model.Menu;

/**
 * Created by ncm on 2017/4/14.
 */

public class BarConfig {

    public static BarConfig inst() {
        return InstHelper.config;
    }

    private BarConfig() {
    }

    public static class InstHelper {
        private final static BarConfig config = new BarConfig();
    }

    private IBarConfig iConfig;

    public void setConfig(IBarConfig config) {
        this.iConfig = config;
    }

    public Menu get(int type) {
        switch (type) {
            case Bar.TYPE_LEFT:
                return getConfig().left();
            case Bar.TYPE_MIDDLE:
                return getConfig().middle();
            case Bar.TYPE_MORE:
                return getConfig().more();
            case Bar.TYPE_RIGHT1:
                return getConfig().right1();
            case Bar.TYPE_RIGHT2:
                return getConfig().right1();
        }
        return getMenu();
    }

    public int getBgColorBar() {
        return getConfig().bgColorBar();
    }

    public int getBgColorMore() {
        return getConfig().bgColorMore();
    }

    private IBarConfig getConfig() {
        if (iConfig == null) {
            iConfig = getDefaultConfig();
        }
        return iConfig;
    }

    private IBarConfig getDefaultConfig() {
        return new IBarConfig() {
            @Override
            public Menu left() {
                return getMenu().type(Bar.TYPE_LEFT).iconId(R.drawable.back_white);
            }

            @Override
            public Menu middle() {
                return getMenu().type(Bar.TYPE_MIDDLE);
            }

            @Override
            public Menu right1() {
                return getMenu().type(Bar.TYPE_RIGHT1);
            }

            @Override
            public Menu right2() {
                return getMenu().type(Bar.TYPE_RIGHT2);
            }

            @Override
            public Menu more() {
                return getMenu().type(Bar.TYPE_MORE);
            }

            @Override
            public int bgColorBar() {
                return MUtil.color(R.color.primary);
            }

            @Override
            public int bgColorMore() {
                return bgColorBar();
            }
        };
    }

    private Menu getMenu() {
        return Menu.newInst()
                .textColor(MUtil.color(R.color.white))
                .textSize(MUtil.dimen(R.dimen.font_normal))
                .descColor(MUtil.color(R.color.white))
                .descSize(MUtil.dimen(R.dimen.font_small))
                .isNotice(false);
    }

}

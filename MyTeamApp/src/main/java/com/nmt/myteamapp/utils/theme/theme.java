/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nmt.myteamapp.utils.theme;

/**
 *
 * @author DELL
 */
public enum theme {
    DEFAULT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new DefaultThemeFactory());
        }
    }, SKY {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new SkyThemeFactory());
        }
    }, LIGHT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new LightThemeFactory());
        }
    };
    
    public abstract void updateTheme();
}

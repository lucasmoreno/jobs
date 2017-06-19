package com.github.rafaelcrz.paggichallenge.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Rafael Felipe on 15/06/2017.
 */

public class FragmentUtil {

    /**
     * Faz a troca de um Fragment por outro
     * @param manager FragmentManager com support
     * @param layout Layout do container que recebera o novo fragment
     * @param fragment Fragment que sera usado
     * @throws Exception Exception catch
     */
    public static void replaceFragment(FragmentManager manager, int layout, Fragment fragment) throws Exception {
        FragmentManager fragmentManager = manager;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }
}

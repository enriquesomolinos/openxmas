package factoriaetsia.com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
public class Configuracion {

    public static final boolean TESTING= true;
    public static final String PROVIDER= LocationManager.GPS_PROVIDER;
    private static Configuracion configuracion = null;
    private static final String FICHERO_CONFIGURACION = "config";
    public static final int INT_DEFECTO= 0;

    private static SharedPreferences prefsPrivate;
    private static Context context;

    public static Configuracion getInstance(Context ctx) {
        if (configuracion == null) {
            configuracion = new Configuracion();
        }
        context = ctx;
        return configuracion;
    }

    public String getConfiguracionString(String clave) {

        return context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE).getString(clave, "");

    }

    public int getConfiguracionInt(String clave) {

        return context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE).getInt(clave, (int)INT_DEFECTO);

    }

    public float getConfiguracionFloat(String clave) {

        return context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE).getFloat(clave,(float) 1);

    }

    public boolean getConfiguracionBoolean(String clave) {

        return context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE).getBoolean(clave, (boolean)false);

    }

    public void putConfiguracion(String clave, String valor) {
        prefsPrivate = context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE);
        Editor prefsPrivateEditor = prefsPrivate.edit();
        prefsPrivateEditor.putString(clave, valor);
        prefsPrivateEditor.commit();

    }

    public void putConfiguracion(String clave, int valor) {
        prefsPrivate = context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE);
        Editor prefsPrivateEditor = prefsPrivate.edit();
        prefsPrivateEditor.putInt(clave, valor);
        prefsPrivateEditor.commit();

    }

    public void putConfiguracion(String clave, float valor) {
        prefsPrivate = context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE);
        Editor prefsPrivateEditor = prefsPrivate.edit();
        prefsPrivateEditor.putFloat(clave, valor);
        prefsPrivateEditor.commit();

    }
    public void putConfiguracion(String clave, boolean valor) {
        prefsPrivate = context.getSharedPreferences(FICHERO_CONFIGURACION,
                Context.MODE_PRIVATE);
        Editor prefsPrivateEditor = prefsPrivate.edit();
        prefsPrivateEditor.putBoolean(clave, valor);
        prefsPrivateEditor.commit();

    }
}

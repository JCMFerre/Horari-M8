package com.reskitow.horari_m8.Widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

import com.reskitow.horari_m8.BD.HorarisSQLiteHelper;
import com.reskitow.horari_m8.Model.Horari;
import com.reskitow.horari_m8.R;

public class HorarioWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.horario_widget);
        Horari horari = new HorarisSQLiteHelper(context).getHorariPerHora();
        boolean comp = (horari != null);
        if (comp) {
            views.setTextViewText(R.id.modul_widget, horari.getModul());
            views.setTextViewText(R.id.profe_widget, context.getString(R.string.txt_professor) + " " + horari.getProfessor());
            views.setTextViewText(R.id.grup_widget, horari.getGrup());
            views.setTextViewText(R.id.horas_widget, context.getString(R.string.txt_hora_inici) + " " + horari.getHoraInici() + " " +
                    context.getString(R.string.txt_hora_final) + " " + horari.getHoraFinal());
            views.setTextViewText(R.id.aula_widget, context.getString(R.string.txt_aula) + " " + horari.getAula());
            views.setViewVisibility(R.id.rl_widget_contenido, View.VISIBLE);
        }
        views.setViewVisibility(R.id.txt_mensaje_widget, comp ? View.GONE : View.VISIBLE);
        views.setViewVisibility(R.id.rl_widget_contenido, comp ? View.VISIBLE : View.GONE);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Se podría mostrar un mensaje conforme se ha creado.
    }

    @Override
    public void onDisabled(Context context) {
        // Se podría mostrar un mensaje conforme se ha eliminado.
    }
}


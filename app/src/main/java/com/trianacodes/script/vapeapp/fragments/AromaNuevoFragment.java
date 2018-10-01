package com.trianacodes.script.vapeapp.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.trianacodes.script.vapeapp.R;
import com.trianacodes.script.vapeapp.actividades.CuadroDialogo;
import com.trianacodes.script.vapeapp.basedatos.DbHelper;
import com.trianacodes.script.vapeapp.basedatos.OperacionesBasesDeDatos;
import com.trianacodes.script.vapeapp.entidades.Aromas;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AromaNuevoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AromaNuevoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AromaNuevoFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner desplegable;
    private SeekBar sbPorcentajeDesde, sbMinMaceracion, sbPorcentajeHasta, sbMaxMaceracion;
    private TextView ePorcentajeDesde, ePorcentajeHasta, eMinMaceracion, eMaxMaceracion;
    private EditText eNombre, eMarca, eObservaciones;
    private String controlVacio;
    private RatingBar valoracion;
    private Button nuevo, imagen;
    OperacionesBasesDeDatos operacionesDatos;
    private DbHelper bd;
    private Aromas aroma = new Aromas();
    private Context mContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AromaNuevoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AromaNuevoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AromaNuevoFragment newInstance(String param1, String param2) {
        AromaNuevoFragment fragment = new AromaNuevoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_aroma_nuevo, container, false);

        eNombre = vista.findViewById(R.id.etNombre);
        eMarca = vista.findViewById(R.id.etMarca);
        sbPorcentajeDesde = vista.findViewById(R.id.sbPorcentajeDesde);
        ePorcentajeDesde = vista.findViewById(R.id.txtPorcentajeDesde);
        sbPorcentajeHasta = vista.findViewById(R.id.sbPorcentajeHasta);
        ePorcentajeHasta = vista.findViewById(R.id.txtPorcentajeHasta);
        sbMinMaceracion = vista.findViewById(R.id.sbMinimo);
        eMinMaceracion = vista.findViewById(R.id.txtMinimo);
        sbMaxMaceracion = vista.findViewById(R.id.sbMaximo);
        eMaxMaceracion = vista.findViewById(R.id.txtMaximo);
        eObservaciones = vista.findViewById(R.id.etObservaciones);
        valoracion = vista.findViewById(R.id.rbValoracion);
        imagen = vista.findViewById(R.id.btnImagen);
        nuevo = vista.findViewById(R.id.btnNuevo);

        // Obtengo una instancia de la base de datos
        operacionesDatos = OperacionesBasesDeDatos.obtenerInstancia(getContext());
        /* Después del new llamo al constructor de la clase. Si este contructor tuviera que recibir
         *  algún parámetro, se tendría que especificar dentro de los paréntesis.*/
        Procesos();
        controlBotones();

        return vista;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void Procesos() {

        try {

            //Llamo a la función que rellena de datos el desplegable
            rellenaDesplegable();
            controlDesplegable();
            /*Llamo a la función que va a controlar si se cambia el valor del SeekBar y si es así,
            modificar el contenido del EditText correspondiente*/
            controlaSbPorcetaje();
            controlaSbMinimo();
            controlaSbMaximo();
             /*Llamo a la función que va a controlar si el contenido del EditText ha cambiado y si es
            así, cambio el valor del SeekBar.*/
            /*controlaEdtPorcentaje();
            controlaEdtMinimo();
            controlaEdtMaximo();*/

        } catch (Exception e) {

            /* Guardo den el SharedPreferences los datos necesarios que hay que mostrar en el
            cuadro de diálogo. Parece que como estoy dentro de un Fragment hay que anteponer al
            getSharedpreferences un objetio de tipo Context (en este caso lo he llamado mContext)*/
            SharedPreferences preferencias = mContext.getSharedPreferences("Dialogos",Context.MODE_PRIVATE);
            SharedPreferences.Editor datosEnviados = preferencias.edit();
            datosEnviados.putString("Titulo",getString(R.string.Errores));
            datosEnviados.putString("Mensaje", getString(R.string.mensaje_error) + " \n" +
                    e.getMessage());
            datosEnviados.apply();
            //Creo un objeto de la clase en la que defino el cuadro de diálogo
            CuadroDialogo dialogoPersonalizado = new CuadroDialogo();
            /*Muestro el cuadro de diálogo pasándo como parámetros el manejador de fragmentos y una
             etiqueta que se va a usar para localizar el cuadro de diálogo para hacer tareas con el
             cuadro de diálogo. He tenido que sustituir el getSupportFragmentManager por
             getFragmentManager ya que estoy llamando a un Fragment desde otro Fragment.*/
            dialogoPersonalizado.show(getFragmentManager(), "personalizado");
            // Creo un objeto de tipo Fragment para almacenar en él el cuadro de diálogo
            android.support.v4.app.Fragment fragmento = getFragmentManager().findFragmentByTag("personalizado");

            // Borro el cuadro de diálogo si no se está mostrando
            if (fragmento != null){

                getFragmentManager().beginTransaction().remove(fragmento).commit();

            }
            //Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    private void rellenaDesplegable() {
    }

    private void controlDesplegable(){

    }

    private void controlaSbPorcetaje(){

    }

    private void controlaSbMinimo(){

    }

    private void controlaSbMaximo(){

    }

    private void controlBotones() {



    }

}

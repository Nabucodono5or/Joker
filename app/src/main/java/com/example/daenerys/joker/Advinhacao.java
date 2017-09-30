package com.example.daenerys.joker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Advinhacao extends AppCompatActivity {

    ImageView left, right, center;
    List<Integer> cartas;
    Random random;
    int cartaSelecionada = 0;
    int resposta;
    Button btnTentar;
    TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advinhacao);

        random = new Random();

        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);
        center = (ImageView) findViewById(R.id.center);

        btnTentar = (Button) findViewById(R.id.btnTentar);
        feedback = (TextView) findViewById(R.id.feedback);

        cartas = new ArrayList<>();

        //Basicamene representam as tres posições das cartas na activity
        //se o valor for 0, é gerado uma carta joker
        //se o valor for 1, é gerado uma carta vitória
        cartas.add(random.nextInt(2));
        cartas.add(random.nextInt(2));
        if((cartas.get(0) == 0) && (cartas.get(1) == 0)) cartas.add(1);
        else cartas.add(0);
    }//onCreate


    //efeito ao selecionar uma carta
    public void cartaLeft(View v){
        cartaSelecionada = 1;
        resposta = cartas.get(0);
        left.setColorFilter(Color.parseColor("#80ffbf"));
        right.clearColorFilter();
        center.clearColorFilter();
    }//cartaLeft


    public void cartaRight(View v){
        cartaSelecionada = 3;
        resposta = cartas.get(2);
        right.setColorFilter(Color.parseColor("#80ffbf"));
        left.clearColorFilter();
        center.clearColorFilter();
    }//cartaRight


    public void cartaCenter(View v){
        cartaSelecionada = 2;
        resposta = cartas.get(1);
        center.setColorFilter(Color.parseColor("#80ffbf"));
        left.clearColorFilter();
        right.clearColorFilter();
    }//cartaCenter


    public void bottonTentar(View v){
        cartas.set(0,random.nextInt(2));
        cartas.set(1,random.nextInt(2));

        if((cartas.get(0) == 0) && (cartas.get(1) == 0)) cartas.set(2,1);
        else cartas.set(2,1);

        btnTentar.setEnabled(false);
        btnTentar.setVisibility(View.INVISIBLE);

        feedback.setText("");

        left.setImageResource(R.drawable.cartaverso);
        right.setImageResource(R.drawable.cartaverso);
        center.setImageResource(R.drawable.cartaverso);

        Animation left_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
        Animation right_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);
        Animation center_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.center);

        left.startAnimation(left_anim);
        right.startAnimation(right_anim);
        center.startAnimation(center_anim);

    }//bottonTentar


    public void bottonConfirmar(View v){
        //gerando imagem da carta
        if (cartaSelecionada == 1){
            left.clearColorFilter();
            if (cartas.get(0) == 0) {
                left.setImageResource(R.drawable.cartajoker);
            }else if (cartas.get(0) == 1){
                left.setImageResource(R.drawable.carta1);
            }
        }//if 1

        if(cartaSelecionada == 2){
            center.clearColorFilter();
            if (cartas.get(1) == 0) {
                center.setImageResource(R.drawable.cartajoker);
            }else if (cartas.get(1) == 1){
                center.setImageResource(R.drawable.carta1);
            }
        }//if 2

        if (cartaSelecionada == 3){
            right.clearColorFilter();
            if (cartas.get(2) == 0) {
                right.setImageResource(R.drawable.cartajoker);
            }else if (cartas.get(2) == 1){
                right.setImageResource(R.drawable.carta1);
            }
        }//if 3

        //ação do resultado
        if (cartaSelecionada != 0 ){
            if (resposta != 0){
                //setar o textview para vitória
                feedback.setText("PARABÉNS VOCÊ GANHOU!");//melhorar o texto
                feedback.setTextColor(Color.parseColor("#00b359"));
            }else {
                //setar o text view para derrota
                feedback.setText("VOCÊ ESCOLHEU O CORINGA! PERDEU!"); //melhorar o texto
                feedback.setTextColor(Color.parseColor("#ff0000"));
            }//if resposta

            btnTentar.setEnabled(true);
            btnTentar.setVisibility(View.VISIBLE);
        }//if cartaSelecionada
    }//bottonConfirmar
}//class

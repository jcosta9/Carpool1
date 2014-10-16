package com.example.carpool.view.viewgroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

@SuppressLint("NewApi")
public class FlyOutContainer extends LinearLayout {

	private View menu; // conteudo do menu
	private View content; // conteudo da p�gina

	protected static final int menuMargin = 150; // Qtd que permanece vis�vel
													// qndo o menu aparece

	public enum MenuState {
		CLOSED, OPEN, CLOSING, OPENING
	};

	// Informa��o de posi��o dos atributos
	protected int currentContentOffset = 0; // Qto do conte�do foi deslocado
	protected MenuState menuCurrentState = MenuState.CLOSED; // Estado atual do
																// menu -
																// default
																// fechado

	// Objetos de Anima��o
	protected Scroller menuAnimationScroller = new Scroller(this.getContext(),
			new SmoothInterpolator()); // localizar a view. Interpolator pega a
										// velocidade da anima��o
	protected Runnable menuAnimationRunnable = new AnimationRunnable(); // pega
																		// a
																		// posi��o
																		// enquanto
																		// ocorre
																		// a
																		// anima��o
	protected Handler menuAnimationHandler = new Handler();

	// Constantes de anima��o
	private static final int menuAnimationDuration = 1000; // dura��o da
															// anima��o
	private static final int menuAnimationPollingInterval = 16;// a cada 16ms a
																// imagem sera
																// gerada
																// novamente

	// Construtores
	public FlyOutContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FlyOutContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlyOutContainer(Context context) {
		super(context);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		this.menu = this.getChildAt(0); // Assumindo que o menu seja sempre o
										// primeiro filho no layout
		this.content = this.getChildAt(1); // Assumindo que o conte�do seja o
											// segundo

		this.menu.setVisibility(View.GONE); // N�o desenha o menu qndo fechado
	}

	/*
	 * 'changed' ser� true se o tamanho do FlyOutContainer for alterado os
	 * outros representam os valores dos cantos do menu
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (changed)
			this.calculateChildDimensions(); // se tiver mudado, ele altera as
												// dimens�es do menu

		this.menu.layout(left, top, right - menuMargin, bottom); // posiciona os
																	// elementos
																	// na tela
																	// A borda
																	// da
																	// direita
																	// corresponde
																	// a borda
																	// total
																	// menos o
																	// conteudo
																	// q sobrou

		// a borda do conte�do ser� igual a borda esquerda com o menu + a
		// largura do menu
		this.content.layout(left + this.currentContentOffset, top, right
				+ this.currentContentOffset, bottom);
	}

	// Abre e fecha o menu
	public void toggleMenu() {
		switch (this.menuCurrentState) {
		case CLOSED: // se fechado, abre-o
			this.menuCurrentState = MenuState.OPENING;
			this.menu.setVisibility(View.VISIBLE); // faz o menu vis�vel
			// Primeiros dois argumentos abaixo s�o a posi��o inicial
			// os proximos dois s�o a qtd q se move em x e em y
			// o ultimo � a dura��o
			this.menuAnimationScroller.startScroll(0, 0, this.getMenuWidth(),
					0, menuAnimationDuration);
			// this.currentContentOffset = this.getMenuWidth(); // a qtd
			// deslocada � igual a largura do menu
			// this.content.offsetLeftAndRight(currentContentOffset); //
			// Modifica horizontalmente a view pelo valor passado
			// this.menuCurrentState = MenuState.OPEN;
			break;
		case OPEN:
			this.menuCurrentState = MenuState.CLOSING;
			this.menuAnimationScroller.startScroll(this.currentContentOffset,
					0, -this.currentContentOffset, 0, menuAnimationDuration);
			// this.content.offsetLeftAndRight(-currentContentOffset);
			// this.currentContentOffset = 0;
			// this.menuCurrentState = MenuState.CLOSED;
			// this.menu.setVisibility(View.GONE);
			break;
		default:
			return;
		}

		this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
				menuAnimationPollingInterval);
		// this.invalidate(); //reflete o que foi alterado na tela
	}

	private int getMenuWidth() {
		return this.menu.getLayoutParams().width;
	}

	private void calculateChildDimensions() {
		this.content.getLayoutParams().height = this.getHeight();
		this.content.getLayoutParams().width = this.getWidth();

		this.menu.getLayoutParams().width = this.getWidth() - menuMargin; // a
																			// largura
																			// �
																			// a
																			// largura
																			// da
																			// tela
																			// -
																			// o
																			// conte�do
																			// que
																			// sobrou
		this.menu.getLayoutParams().height = this.getHeight(); // a altura do
																// menu � a
																// mesma
	}

	private void adjustContentPosition(boolean isAnimationOngoing) {
		int scrollerOffset = this.menuAnimationScroller.getCurrX(); //atual posicao x
		
		this.content.offsetLeftAndRight(scrollerOffset //pega a nova posicao ao subtrair o que deveria ser pelo
				- this.currentContentOffset);          // resultado do ultimo
		
		this.currentContentOffset = scrollerOffset;
		this.invalidate();
		
		if (isAnimationOngoing) //caso ainda esteja acontecendo, ele chamara o metodo apos o tempo especificado(16ms)
			this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
					menuAnimationPollingInterval);
		else
			this.onMenuTransitionComplete();
	}

	private void onMenuTransitionComplete() {
		switch (this.menuCurrentState) {
		case OPENING:
			this.menuCurrentState = MenuState.OPEN;
			break;
		case CLOSING:
			this.menuCurrentState = MenuState.CLOSED;
			this.menu.setVisibility(View.GONE);
			break;
		default:
			return;
		}
	}

	//Altera a variacao do LinearInterpolator fazendo-o mais agradavel
	protected class SmoothInterpolator implements Interpolator {
		@Override
		public float getInterpolation(float t) {
			return (float) Math.pow(t - 1, 5) + 1;
		}
	}

	protected class AnimationRunnable implements Runnable {
		@Override
		public void run() { //Determina qual a posi��o da view deveria ser naquele ponto da anima��o
			boolean isAnimationOngoing = FlyOutContainer.this.menuAnimationScroller
					.computeScrollOffset(); //true se a animacao estiver rolando
			FlyOutContainer.this.adjustContentPosition(isAnimationOngoing);
		}
	}
}

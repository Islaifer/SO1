package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCruzamento extends Thread{
	private int idCarro;
	private Semaphore semaforo;
	private static int ordemDeSaida;
	
	public ThreadCruzamento(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		Ordem();
		try {
			semaforo.acquire();
			Saindo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Saida();
			semaforo.release();
		}
	}
	
	private void Ordem() {
		Random random = new Random();
		int sorte = random.nextInt(120);
		try {
			sleep(sorte);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void Saindo() {
		Random random = new Random();
		int vm = random.nextInt(120);
		try {
			sleep(vm);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void Saida() {
		ordemDeSaida++;
		System.out.println("O carro " + this.idCarro + " foi o " + ordemDeSaida + "º"
				+ " a cruzar o cruzamento!");
	}
}

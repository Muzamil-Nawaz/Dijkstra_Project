/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


/**
 *
 * @author ADMIN
 */
public class CannonBallProblem {
    private class Vertex implements Comparable<Vertex> {
		int start;
		double end;
		public int compareTo(Vertex o) {
			if (end > o.end) {
				return 1;
			} else if (end < o.end) {
				return -1;
			}
			return 0;
		}
		public Vertex(int i, double d) {
			start = i;
			end= d;
		}
	}
	InputStreamReader input;
        BufferedReader reader;
	public void start() throws IOException {
            input = new InputStreamReader(System.in);
            reader = new BufferedReader(input);
            String str  =reader.readLine();
            String s[] = str.split(" "); 
            float s11 = Float.parseFloat(s[0]);
            float s12 = Float.parseFloat(s[1]);
            str  =reader.readLine();
            String d[] = str.split(" ");
            float d11 = Float.parseFloat(d[0]);
            float d12 = Float.parseFloat(d[1]);
            int totalCan = Integer.parseInt(reader.readLine());
            double[][] m = new double[totalCan+2][totalCan+2];
            double[][] cn = new double[totalCan][2];
            for (int i = 0; i < totalCan; i++) {
                String[] ss = reader.readLine().split(" ");            
		cn[i] = new double[]{Float.parseFloat(ss[0]), Float.parseFloat(ss[1])};
            }
            m[0][1] = Math.hypot(Math.abs(s11-d11), Math.abs(s12-d12))/5;
            m[1][0] = m[0][1];
            for (int i = 0; i < totalCan; i++) {
                double end = Math.hypot(Math.abs(s11-cn[i][0]), Math.abs(s12-cn[i][1]));
		m[0][i+2] = end/5;
		m[i+2][0] = Math.abs(end-50)/5+2;
            }
            for (int i = 0; i < totalCan; i++) {
                double end = Math.hypot(Math.abs(d11-cn[i][0]), Math.abs(d12-cn[i][1]));
		m[1][i+2] = end/5;
		m[i+2][1] = Math.abs(end-50)/5+2;
            }
            for (int i = 0; i < totalCan; i++) {
                for (int e = 1; e < totalCan; e++) {
                    double end = Math.hypot(Math.abs(cn[e][0]-cn[i][0]),
                    Math.abs(cn[e][1]-cn[i][1]));
                    m[i+2][e+2] = Math.abs(end-50)/5+2;
                    m[e+2][i+2] = m[i+2][e+2];
		}
            }
            double[] t = new double[totalCan+2];
            Arrays.fill(t, Double.MAX_VALUE);
            algo(t,m);
            System.out.println(t[1]);
		
		
	}
	
	public void algo(double[] t,double[][] m) {
		boolean[] cannonPath = new boolean[t.length];
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
                Vertex v = new Vertex(0,0);
		queue.add(v);
		while (!queue.isEmpty()) {
                    Vertex i = queue.remove();
                       if (cannonPath[i.start]) {
				continue;
			}
			cannonPath[i.start] = true;
                        int j=0;
                        while(j<t.length){
                            if (j != i.start && !cannonPath[j] && i.end+m[i.start][j] < t[j]) {
					t[j] = i.end+m[i.start][j];
					queue.add(new Vertex(j, t[j]));
				}
                            j++;
                        }	
                }
	}
	
	public static void main(String[] args) throws IOException {
		new CannonBallProblem().start();
	}
}


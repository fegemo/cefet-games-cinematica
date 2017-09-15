/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input;

/**
 *
 * @author Aluno
 */
public class Chegar extends AlgoritmoMovimentacao  {
    private static final char NOME = 'c';
    private static final float timeToTarget = 0.25f;
    private static final float raio = 5;

    public Chegar(float maxVelocidade) {
        super(NOME);
        this.maxVelocidade = maxVelocidade;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        output.velocidade = alvo.getObjetivo().sub(agente.posicao);
        if (output.velocidade.len() < raio) {
            return new Direcionamento(0,0,0);
        }
        output.velocidade.scl(timeToTarget);
        
        if (output.velocidade.len() > maxVelocidade) {
            output.velocidade.nor();
            output.velocidade.scl(maxVelocidade);    
        }
        
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        output.rotacao = 0;
        // calcula que direção tomar (configura um objeto Direcionamento 
        // e o retorna)
        // ...
        // super.alvo já contém a posição do alvo
        // agente (parâmetro) é a pose do agente que estamos guiando
        // ...
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Input.Keys.C;
    }
}

package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';
    private static final double radius = 0.25;
    private static final float timeToTarget = 1/4;  

    public Chegar(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }

    protected Chegar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();

        output.velocidade = super.alvo.getObjetivo().sub(agente.posicao);
        
        if (output.velocidade.len() < this.radius)
            return null;
        
        output.velocidade.mulAdd(output.velocidade, 1/timeToTarget);
        
        if (output.velocidade.len() > this.maxVelocidade) {
            output.velocidade.nor();
            output.velocidade.mulAdd(output.velocidade, maxVelocidade);
        }            
        
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        output.rotacao = 0;
        
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
}

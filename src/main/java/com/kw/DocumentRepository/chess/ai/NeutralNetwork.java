package com.kw.DocumentRepository.chess.ai;

public class NeutralNetwork {
    private int layersCount;
    private int[] layersSizes;
    private int[][][] neuronsWeights;
    private int[][] neuronsBias;

    public NeutralNetwork(int layersCount, int[] layersSizes) {
        this.layersCount = layersCount;
        this.layersSizes = layersSizes;
        for(int layer = 0; layer < layersCount; layer++) {
            //for(int neuron = 0; neuron < layersSizes[layer]; )
        }
    }
}

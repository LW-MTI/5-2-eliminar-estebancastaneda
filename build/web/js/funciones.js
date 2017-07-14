/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function rellenarCadena(cad, caracter, numCaracteres, porIzq){
    filled = cad;
    for(i=0; i<(numCaracteres - cad.length); i++){
        if(porIzq === true){
            filled = caracter + filled;
        }else{
            filled = filled + caracter;
        }
    }
    return filled;
}
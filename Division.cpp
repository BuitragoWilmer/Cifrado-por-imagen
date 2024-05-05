#include <iostream>
#include <fstream>
#include <string>

using namespace std;

// Declaración de la función

void escribirEnArchivo(ofstream& archivo, const string& texto, bool selector);
int binarioADecimal(const string& binario);
string Convert (float number);
static string bitsRestantes;
string agregarBits(string bits);
static string bitsAcumulados;


int main(int argc, char *argv[]) {
	string s = argv[2];
	
	int dimension;
    sscanf(s.c_str(), "%d", &dimension);

   	int division = dimension - 8;
   if (argc > 1) {
	    // Cadena de ejemplo
	    string cadena = argv[1]; // Ejemplo de cadena, deberías pasar la cadena desde Java
	    
	    // Abrir los archivos
	    ofstream archivo1("almacenador.txt");
	    ofstream archivo2("Sobrante.txt");
	    
	    // Verificar si los archivos se abrieron correctamente
	    if (!archivo1.is_open() || !archivo2.is_open()) {
	        cerr << "Error al abrir los archivos" << endl;
	        return 1;
	    }
	    
	    // Iterar sobre la cadena
	    for (size_t i = 0; i < cadena.length();) {
	        // Determinar la longitud del próximo segmento y la actualización de la posición de la cadena
	        size_t longitud = (i % dimension == 8) ? division : 8;
	        
	        // Obtener la subcadena
	        string subcadena = cadena.substr(i, longitud);
	        
	        // Determinar el archivo en el que escribir
	        bool selector = i % dimension < 8;
	        ofstream& archivo = selector ? archivo1 : archivo2;
	        
	        // Escribir en el archivo correspondiente
	        escribirEnArchivo(archivo, subcadena, selector);
	        
	        // Actualizar la posición de la cadena
	        i += longitud;
	    }
	    
	    if(bitsAcumulados!=""){
	   		archivo2<<bitsAcumulados;	
		}
	    
	    // Cerrar los archivos
	    archivo1.close();
	    archivo2.close();
    } else {
        cerr << "No se pasaron argumentos desde Java" <<endl;
        return 1; // Devolver un código de error
    }
    return 0;
}

// Implementación de la función
void escribirEnArchivo(ofstream& archivo, const string& texto, bool selector) {
    if(selector){ 	
    	archivo <<binarioADecimal(texto)<<" ";
	}else{
		string byte =agregarBits(texto);
				
		if(byte!=""){
			archivo <<binarioADecimal(byte)<<" ";   
		}
	}
}


int binarioADecimal(const string& binario) {
    int decimal = 0;
    int longitud = binario.length();
    
    for (int i = 0; i < longitud; ++i) {
        if (binario[i] == '1') {
            decimal |= (1 << (longitud - i - 1));
        }
    }
    
    return decimal;
}


string agregarBits(string bits) {
    static string bitsRestantes;
    for (char bit : bits) {
        if (bitsAcumulados.size() < 8) {
            bitsAcumulados += bit;
        } else {
            bitsRestantes += bit;        
        }
    }
    
    if (bitsAcumulados.size() == 8) {
        string resultado = bitsAcumulados;
        bitsAcumulados = bitsRestantes;
        bitsRestantes = "";
        return resultado;
    }
    return "";
}




/* Codigo de usuario */
package midik.pruebaspractica1compi1.jlex;
import java_cup.runtime.Symbol;
import midik.pruebaspractica1compi1.jcup.sym;
import midik.pruebaspractica1compi1.objetos.Error;
import java.util.ArrayList;
%% /* Opciones, declaraciones y macros */
%class AnalizadorLexico
%type Symbol
%unicode
%public
%cup
%line
%column

%{

    private ArrayList<Error> errores = new ArrayList<>();

    private void agregarError(String lexema, int linea, int columna, String tipo, String descripcion){
        errores.add(new Error(lexema, linea, columna, tipo, descripcion));
    }

    public ArrayList<Error> getErrores(){
        return this.errores;
    }

    private Symbol symbol(int type) {
    return new Symbol(type, yycolumn+1, yyline+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yycolumn+1, yyline+1, value);
  }

%}

//DECLARACION DEL ALFABETO PARA LOS TOKENS
//Palabras reservadas 
DEF = "Def" | "def"
BARRAS = "Barras"
PIE = "Pie"
TITULO = "titulo"
EJEX = "ejex"
EJEY = "ejey"
ETIQUETAS = "etiquetas"
VALORES = "valores"
UNIR = "unir"
TIPO = "tipo"
CANTIDAD = "Cantidad"
PORCENTAJE = "Porcentaje"
TOTAL = "total"
EXTRA = "extra"
EJECUTAR = "Ejecutar"

//Operadores aritmeticos
SIGNO_MAS = "+"
SIGNO_MENOS = "-"
SIGNO_POR = "*"
SIGNO_DIVISION = "/"

//Signos de agrupacion
PARENTESIS_APERTURA = "("
PARENTESIS_CERRAR = ")"
CORCHETE_APERTURA = "["
CORCHETE_CERRAR = "]"
LLAVE_APERTURA = "{"
LLAVE_CERRAR = "}"

//Signos de puntuacion
DOS_PUNTOS = ":"
PUNTO_COMA = ";"
COMA = ","

//Partes (Estos no son un tipo de token, solo son necesarios para la construcciones de VALORES)
PUNTO = "."
COMILLAS_DOBLES = "\""
FIN_LINEA = \r|\n|\r\n|\u2028|\u2029|\u000B|\u000C|\u0085
ESPACIO = " " | "\t" | {FIN_LINEA}
NUMERO = [0-9] 
LETRA = [a-zA-Z]

//Valores
PALABRA = ({COMILLAS_DOBLES})({NUMERO}|{LETRA} | {ESPACIO})*({NUMERO}|{LETRA}) ({NUMERO}|{LETRA} | {ESPACIO})* ({COMILLAS_DOBLES})
ENTERO = {NUMERO}{NUMERO}*
DECIMAL = {NUMERO}{NUMERO}* {PUNTO} {NUMERO}{NUMERO}*

//Comentarios
COMENTARIO = "#"~{FIN_LINEA}

%% /* Reglas lexicas y acciones */t
{DEF}                                   {return symbol(sym.DEF, yytext());} 
{BARRAS}                            {return symbol(sym.BARRAS, yytext());} 
{PIE}                                    {return symbol(sym.PIE, yytext());}
{TITULO}                              {return symbol(sym.TITULO, yytext());}
{EJEX}                                  {return symbol(sym.EJEX, yytext());} 
{EJEY}                                   {return symbol(sym.EJEY, yytext());} 
{ETIQUETAS}                       {return symbol(sym.ETIQUETAS, yytext());}
{VALORES}                           {return symbol(sym.VALORES, yytext());} 
{UNIR}                                  {return symbol(sym.UNIR, yytext());} 
{TIPO}                                   {return symbol(sym.TIPO, yytext());} 
{CANTIDAD}                         {return symbol(sym.CANTIDAD, yytext());}
{PORCENTAJE}                     {return symbol(sym.PORCENTAJE, yytext());} 
{TOTAL}                                {return symbol(sym.TOTAL, yytext());} 
{EXTRA}                                {return symbol(sym.EXTRA, yytext());} 
{EJECUTAR}                          {return symbol(sym.EJECUTAR, yytext());}
{SIGNO_MAS}                       {return symbol(sym.SIGNO_MAS, yytext());}
{SIGNO_MENOS}                  {return symbol(sym.SIGNO_MENOS, yytext());}
{SIGNO_POR}                        {return symbol(sym.SIGNO_POR, yytext());} 
{SIGNO_DIVISION}               {return symbol(sym.SIGNO_DIVISION, yytext());}
{PARENTESIS_APERTURA}   {return symbol(sym.PARENTESIS_A, yytext());}
{PARENTESIS_CERRAR}       {return symbol(sym.PARENTESIS_C, yytext());} 
{CORCHETE_APERTURA}     {return symbol(sym.CORCHETE_A, yytext());} 
{CORCHETE_CERRAR}         {return symbol(sym.CORCHETE_C, yytext());} 
{LLAVE_APERTURA}              {return symbol(sym.LLAVE_A, yytext());} 
{LLAVE_CERRAR}                  {return symbol(sym.LLAVE_C, yytext());}
{DOS_PUNTOS}                     {return symbol(sym.DOS_PUNTOS, yytext());}
{PUNTO_COMA}                    {return symbol(sym.PUNTO_COMA, yytext());} 
{COMA}                                  {return symbol(sym.COMA, yytext());}
{PALABRA}                             {return symbol(sym.PALABRA, yytext());}
{ENTERO}                              {return symbol(sym.ENTERO, new Integer(yytext()));}
{DECIMAL}                             {return symbol(sym.DECIMAL, new Double(yytext()));}
{COMENTARIO}                     {}
{FIN_LINEA}                           {}
{ESPACIO}                              {}
.                                                {agregarError(yytext(), yyline, yycolumn, "Lexico","El simbolo no existe en el lenguaje");}
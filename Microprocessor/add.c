Sint add(int x, int y){
	asm mov AX, x
	asm mov BX, y
	asm ADD AX, BX
}

int main(void){

	printf("5+100=%Ld\n",add(5,100));
	return(0);

}
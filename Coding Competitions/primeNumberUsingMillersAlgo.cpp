#define ll long long
ll mulmod(ll a, ll b, ll c){
	
	return (a*b)%c;

}
ll modulo(ll a, ll b, ll c)
{
	ll x = 1, y = a%c;
	while(b>0)
	{
		if(b&1) x = mulmod(x,y,c);
		y = mulmod(y,y,c);
		b = b>>1;
	}
	return x;
}
bool isprime(ll p, int iter){

	if(p<2) return false;
	if(p==2) return true;
	if(!(p&1)) return false;
	ll s = p-1, a, temp, mod;
	while(!(s&1)) s = s>>1;
	for(int i=0; i<iter; i++){

		a = rand()%(p-1)+1;
		temp = s;
		mod = modulo(a, temp, p);
		while(temp!=p-1 && mod!=1 && mod!=p-1){

			mod = mulmod(mod, mod, p);
			temp = temp<<1;

		}
		if(mod!=p-1 && !(temp&1)) return false;
	}
	return true;

}
int main(){

	//to find if any number is prime or not just call isprime(number,30)
	//example
	cout<<isprime(1000002,30);
	return 0;

}
//#include <bits/stdc++.h>
    #include <cstring>
    #include <vector>
    #include <list>
    #include <map>
    #include <set>
    #include <deque>
    #include <stack>
    #include <bitset>
    #include <algorithm>
    #include <functional>
    #include <numeric>
    #include <utility>
    #include <sstream>
    #include <iostream>
    #include <iomanip>
    #include <cstdio>
    #include <cmath>
    #include <cstdlib>
    #include <ctime>
    #include <memory.h>
    #include <cassert>
    #include <climits>
    using namespace std;
    //end of header files

    #define inf INT_MAX
    #define NO 100005
    #define MOD 1000000007

    #define F(i,a,b) for(int i=(a);i<(b);i++)
    #define RI(n) F(i,0,n)
    #define RJ(n) F(j,0,n)
    #define RK(n) F(k,0,n)

    #define MIN(a,b) ((a) < (b) ? (a) : (b))
    #define MAX(a,b) ((a) > (b) ? (a) : (b))

    #define Clear(a) memset(a,0,sizeof(a))              //clearing memory of an array
    #define setfalse(a) memset(a,false,sizeof(a))       //setting the array into false
    #define settrue(a) memset(a,true,sizeof(a))         //setting the array into true
    #define clrstr(a) memset(a,'\0',sizeof(a))          //setting string array to null
    #define setarray(a,v) memset(a,v,sizeof(a))

    #define open freopen("input.txt","r",stdin)         //opening input file
    #define close freopen ("output.txt","w",stdout)     //opening output file

    #define Case(a) printf("Case %d: ",a)               //printing case number
    #define caseh(a) printf("Case #%d: ",a)             //printing case number having '#'
    #define getcase(a) scanf("%d",&a)                   //scanning case number
    #define caseloop(a,b) for(a=1;a<=b;a++)  

    #define inp(n) scanf("%d",&n)
    #define inp2(n,m) scanf("%d%d",&n,&m)
    #define ins(s) scanf("%s",s)
    #define out(n) printf("%d\n",n)
    #define out2(n,m) printf("%d %d\n",n,m)

    #define inll(n) scanf("%I64d",&n)
    #define inll2(n,m) scanf("%I64d%I64d",&n,&m)
    #define outll(n) printf("%I64d\n",n)
    #define outll(n) printf("%I64d\n",n)
    #define outll2(n,m) printf("%I64d %I64d\n",n,m)
    #define int_bits __builtin_popcount
    #define ll_bits __builtin_popcountll

    #define take(ar,n) R(n)cin>>ar[i] ;
    #define ctake(ar,n,br) RI(n){cin>>ar[i];br[i]=ar[i];}
    //#define print(ar,n) RI(n)//cout<<ar[i]<<" "; 

    typedef long long int LL;
    //fast input
    #define getchar getchar_unlocked()
    #define putchar putchar_unlocked()
    int scan_d() {int ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long scan_ld() {long ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long long scan_lld() {long long ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long long unsigned scan_llu() {long long unsigned ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
     

    //For Integer use of below inp function is preferable as it is fast as compared to scan_d()
    inline void inpfast( int &n ) {
        
        n=0;
        int ch=getchar,sign=1;
        while( ch < '0' || ch > '9' ){if(ch=='-')sign=-1; ch=getchar;}
        while( ch >= '0' && ch <= '9' )
        n=(n<<3)+(n<<1)+ ch-'0', ch=getchar;
        n=n*sign;

    }
    

    LL segment[ NO<<2 ] , adda[ NO<<2 ] , mula[ NO<<2 ] ;
    LL arr[ NO ];


    void build( int node , int a , int b ){
    
        mula[ node ] = 1 ; 
        if( a == b ){

            segment[ node ] = arr[ a ] ;
            ////cout<<a<<","<<b<<" -- node = "<<node<<" : "<<endl ; 
            ////cout<<segment[ node ]<<endl ; 
            return ;

        }

        build( 2 * node , a , ( a + b ) / 2 ) ;
        build( 2 * node + 1 , ( a + b ) / 2 + 1 , b ) ;
        ////cout<<a<<","<<b<<" -- node = "<<node<<" : "<<endl ; 
        segment[ node ] = ( segment[ 2 * node ] + segment[ 2 * node + 1 ] ) % MOD ; 
        
        ////cout<<" 2 * node = "<<segment[ 2 * node ]<<endl ; 
        ////cout<<" 2 * node + 1 = "<<segment[ 2 * node + 1]<<endl ; 

    }


    LL query( int node , int a , int b , int x , int y ){

        if( a > y || b < x ) return 0LL ;

        if( a >= x && b <= y ) return segment[ node ] ;

        LL sum1 = query( 2 * node , a , ( a + b ) / 2 , x , y );
        LL sum2 = query( 2 * node + 1 , ( a + b ) / 2 + 1 , b , x , y );

        return ( ( sum1 + sum2 ) % MOD );

    }

    LL queryNew( int node , int a , int b , int x , int y , LL addition , LL multiplication ){

        if( a > y || b < x ){
        
            //segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            //segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;

            //adda[ node ] = ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) )  ;
            //mula[ node ] = ( mula[ node ] * multiplication ) % MOD ;

            return 0LL ;
        
        }

        if( a >= x && b <= y ) {

            //segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            //segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;

            //adda[ node ] = ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) )  ;
            //mula[ node ] = ( mula[ node ] * multiplication ) % MOD ;
            
            LL temp = segment[node] ; 
            temp = (temp*multiplication)%MOD ; 
            temp = ( temp + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;

            return temp ;

        }

        LL sum1 = queryNew( 2 * node , a , ( a + b ) / 2 , x , y , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;
        LL sum2 = queryNew( 2 * node + 1 , ( a + b ) / 2 + 1 , b , x , y , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;

        //adda[ node ] = 0 ; mula[ node ] = 1 ; 

        return ( ( sum1 + sum2 ) % MOD );

    }

    void updateAdd( int node , int a , int b ,int x , int y , LL val , LL addition , LL multiplication ){
       
        //cout<<"called "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<" adda[node] = "<<adda[node]<<endl ; 
        
        if( a > y || b < x ){
        
            segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;
            
            
            adda[ node ] = ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) )  ;
            mula[ node ] = ( mula[ node ] * multiplication ) % MOD ;
            //cout<<"first For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
            //cout<<"making segment[ node ] = "<<segment[node] ; 
            //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 
            
            return ; 
        
        }

        if( a >= x && b <= y ){

            segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;
            segment[ node ] = ( segment[ node ] + ( val * ( b - a + 1 ) ) % MOD )%MOD ;

            adda[ node ] = ( ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) + val ) % MOD )  ;
            mula[ node ] = ( mula[ node ] * multiplication ) % MOD ;
            
            //cout<<"second For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
            //cout<<"making segment[ node ] = "<<segment[node] ; 
            //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 

            return ;

        }

        updateAdd( 2 * node , a , ( a + b ) / 2 , x , y , val , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;
        updateAdd( 2 * node + 1 , ( a + b ) / 2 + 1 , b , x , y , val , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;

        adda[ node ] = 0 ; mula[ node ] = 1 ; 

        segment[ node ] = ( segment[ 2 * node ] + segment[ 2 * node + 1 ] ) % MOD;
        
        //cout<<"Third For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
        //cout<<"making segment[ node ] = "<<segment[node] ; 
        //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 

    }

    void updateMul( int node , int a , int b ,int x , int y , LL val , LL addition , LL multiplication ){

        //cout<<"called "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<" adda[node] = "<<adda[node]<<endl ; 
        
        if( a > y || b < x ){
        
            segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;

            adda[ node ] = ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) )  ;
            mula[ node ] = ( mula[ node ] * multiplication ) % MOD ;
            
            //cout<<"First For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
            //cout<<"making segment[ node ] = "<<segment[node] ; 
            //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 
            return ; 
        
        }

        if( a >= x && b <= y ){

            segment[ node ] = ( segment[ node ] * multiplication )%MOD ;
            segment[ node ] = ( segment[ node ] + ( addition * ( b - a + 1 ) ) % MOD )%MOD ;
            segment[ node ] = ( segment[ node ] * val )%MOD ;

            adda[ node ] = ( ( ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) * val ) % MOD )  ;
            mula[ node ] = ( ( ( mula[ node ] * multiplication ) % MOD ) * val ) % MOD ; 
            
            //cout<<"SEcond For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
            //cout<<"making segment[ node ] = "<<segment[node] ; 
            //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 

            return ;

        }

        updateMul( 2 * node , a , ( a + b ) / 2 , x , y , val , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;
        updateMul( 2 * node + 1 , ( a + b ) / 2 + 1 , b , x , y , val , ( ( (( adda[ node ] * multiplication )%MOD) + addition ) % MOD ) , ( multiplication * mula[ node ] ) % MOD ) ;

        adda[ node ] = 0 ; mula[ node ] = 1 ; 

        segment[ node ] = ( segment[ 2 * node ] + segment[ 2 * node + 1 ] ) % MOD;
        
        //cout<<"third For "<<a<<" , "<<b<<" : addition = "<<addition<<" multiplication = "<<multiplication<<" mula[node] = "<<mula[node]<<endl ; 
        //cout<<"making segment[ node ] = "<<segment[node] ; 
        //cout<<" adda[ node ] = "<<adda[node]<<" mula[ node ] = "<<mula[ node ]<<endl ; 

    }


    int main(){

        int n , q , t , x , y ;
        LL v ; 

        inpfast( n ) ; inpfast( q ) ;

        RI( n ){ 

            scanf( "%lld" , &arr[ i ] ) ; 
            //cin>>arr[ i ] ;

        }

        build( 1 , 0 , n - 1 ) ; 

        F( queries , 0 , q ){

            inpfast(t) ; 

            if( t == 1 ){

                inpfast( x ) ; inpfast( y ) ; 
                scanf( "%lld" , &v ) ; 

                //updateADD( 1 , 0 , n - 1 , x - 1 , y - 1 , v ) ; 
                updateAdd( 1 , 0 , n - 1 , x - 1 , y - 1 , v , 0 , 1 ) ; 
                //cout<<endl<<endl ; 

            }
            else if( t == 2 ){

                inpfast( x ) ; inpfast( y ) ; 
                scanf( "%lld" , &v ) ; 
                
                //updateMUL( 1 , 0 , n - 1 , x - 1 , y - 1 , v ) ; 
                updateMul( 1 , 0 , n - 1 , x - 1 , y - 1 , v , 0 , 1 ) ; 
                //cout<<endl<<endl ; 

            }
            else if( t == 3 ){

                inpfast( x ) ; inpfast( y ) ;
                scanf( "%lld" , &v ) ; 
                //updateSET( 1 , 0 , n - 1 , x - 1 , y - 1 , v ) ;                 
                updateMul( 1 , 0 , n - 1 , x - 1 , y - 1 , 0LL , 0 , 1 ) ; 
                updateAdd( 1 , 0 , n - 1 , x - 1 , y - 1 , v , 0 , 1 ) ; 

            }
            else{

                inpfast( x ) ; inpfast( y ) ;
                LL ans = queryNew( 1 , 0 , n - 1 , x - 1 , y - 1 , 0 , 1 ) ; 
                printf( "%lld\n" , ans ) ;
                ////cout<<ans<<endl ; 

            }


        }

        return 0 ; 

    }
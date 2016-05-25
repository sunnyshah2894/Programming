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
    #define print(ar,n) RI(n)cout<<ar[i]<<" "; 
    
    #define fastInput ios_base::sync_with_stdio(false)
    typedef long long int LL;
    
    bool descending(int a,int b){
        return a>b;
    }

    LL findMax( LL , LL , int , int , int , int) ; 
    int main(){
        
        fastInput ; 
        int n , m ;
        cin>>n>>m ; 

        char ch[ n + 1 ][ m + 1 ] ;
        LL rightMax[ 20 ][ 20 ] ; 
        LL downMax[ 20 ][ 20 ] ; 
        LL max[ 20 ][ 20 ] ;

        RI( 20 ){

            RJ( 20 ){

                rightMax[ i ][ j ] = 0 ;
                downMax[ i ][ j ] = 0 ;
                max[ i ][ j ] = 0 ; 
            }
        }


        RI( n ){

            RJ( m ){

                cin>>ch[ i + 1 ][ j + 1 ] ; 

            }

        }


        LL maxValue = 0 , temp ;
        F( row , 1 , n + 1 ){

            F( col , 1 , m + 1 ){

                if( ch[ row ][ col ] == 'G' ){
                 
                    temp = 1 ;
                    maxValue = 100 ; 
                    for( int i = row + 1 ; i < n + 1 ; i++ )  // checking downwards 
                        if( ch[ i ][ col ] == 'G' )temp++ ;
                        else break ; 
                    maxValue = MIN( maxValue , temp ) ; 

                    temp = 1 ; 
                    for( int i = row - 1 ; i > 0 ; i-- )   // checking upwards
                        if( ch[ i ][ col ] == 'G' )temp++ ; 
                        else break ;
                    maxValue = MIN( maxValue , temp ) ;

                    temp = 1 ; 
                    for( int j = col + 1 ; j < m + 1 ; j++ )   // checking right
                        if( ch[ row ][ j ] == 'G' )temp++ ; 
                        else break ;
                    maxValue = MIN( maxValue , temp ) ;

                    temp = 1 ; 
                    for( int j = col - 1 ; j > 0 ; j-- )   // checking left
                        if( ch[ row ][ j ] == 'G' )temp++ ; 
                        else break ;
                    maxValue = MIN( maxValue , temp ) ;

                    max[ row ][ col ] = maxValue ; 
                }

            }

        }


        LL ans = 0 ; 
        F( row , 1 , n + 1 ){

            F( col , 1 , m + 1 ){
                //cout<<row<<","<<col<<max[row][col]<<endl ; 
                if( ch[ row ][ col ] == 'G' ){


                    F( row1 , 1 , n + 1 ){

                        F( col1 , 1 , m + 1 ){  
                        
                            if( ch[ row1 ][ col1 ] == 'G' ){
                                
                                LL temp = findMax( max[ row ][col] , max[ row1 ][col1] , row,col,row1,col1 ) ; 
                                ans = MAX( ans , temp ) ;
                                //cout<<row<<","<<col<<"::"<<row1<<","<<col1<<" = "<<ans<<endl ; 
                            
                            }
                        
                        }
                        
                    }

                        
                }

            }

        }
        cout<<ans<<endl ;

        return 0 ;

    }

    LL findMax( LL AMax , LL BMax , int r , int c , int r1 , int c1  ){
    
        LL sumReqLeft = abs( c1 - c ) + 1 ;
        LL sumReqDown = abs( r1 - r ) + 1 ;
        
        LL ans = 0 ; 
        
        if( r1 == r  ){
        
            F( i , 1 , AMax + 1 ){
            
                F( j , 1 , BMax + 1 ){
                
                    if( sumReqLeft >= i + j ){
                    //  cout<<i<<j<<endl ; 
                        ans = MAX( ans , (( i - 1 )*4 + 1) * ( ( j - 1)*4 + 1 ) ) ;
                    
                    }
                        
                }
                
            }
        }
        else if( c1 == c ){
        
            F( i , 1 , AMax + 1 ){
            
                F( j , 1 , BMax + 1 ){
                
                    if( sumReqDown >= i + j ){
                    
                    //  cout<<i<<j<<endl ; 
                        ans = MAX( ans , (( i - 1 )*4 + 1) * ( ( j - 1)*4 + 1 ) ) ;
                    
                    }
                        
                }
                
            }
        
        }
        else{
            
            if( r > r1 ){

                int r2 = r , c2 = c ; 
                r = r1 ; c = c1 ;
                r1 = r2 ; c1 = c2 ; 

            }
            if( c1 < c ){// anti diag
                //cout<<"anti diag" ; 
                F( i , 1 , AMax + 1 ){
                
                    F( j , 1 , BMax + 1 ){
                    
                        if( (r1-j+1 <= r && c + i - 1 <= c1)  || ( r+i - 1 >=r1 && c <= c1+j - 1 ) ){
                        
                            
                        
                        }
                        else{
                            
                            //cout<<i<<j<<endl ; 
                            ans = MAX( ans , (( i - 1 )*4 + 1) * ( ( j - 1)*4 + 1 ) ) ;
                            
                        }
                            
                    }
                    
                }

            }
            else{
                F( i , 1 , AMax + 1 ){
                
                    F( j , 1 , BMax + 1 ){
                    
                        if( (r1-j+1 <= r && c + i - 1 >= c1)  || ( r+i - 1 >=r1 && c >= c1-j + 1 ) ){
                        
                            
                        
                        }
                        else{
                            
                        //  cout<<i<<j<<endl ; 
                            ans = MAX( ans , (( i - 1 )*4 + 1) * ( ( j - 1)*4 + 1 ) ) ;
                        
                        }
                            
                    }
                    
                }
            }
            
        
        }
        
        
        return ans ; 
        
    }

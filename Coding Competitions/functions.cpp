
    if ( a / b  ) % m = x 

        then  m*c + x = a / b   where c is a constant

              m*b*c + x*b = a 

   therefore  a % ( m * b ) = x * b 

              ( a % ( m * b ) ) / b = x



/* this function calculates (a*b)%c taking into account that a*b might overflow */
///////////////////////////////////////
long long mulmod(long long a,long long b,long long c){
    long long x = 0,y=a%c;
    while(b > 0){
        if(b%2 == 1){
            x = (x+y)%c;
        }
        y = (y*2)%c;
        b /= 2;
    }
    return x%c;
}
//////////////////////////////////////////////////////////////
//count no of bits set in a numbers binary form

unsigned int popcount64(unsigned long long x)
{
    x = (x & 0x5555555555555555ULL) + ((x >> 1) & 0x5555555555555555ULL);
    x = (x & 0x3333333333333333ULL) + ((x >> 2) & 0x3333333333333333ULL);
    x = (x & 0x0F0F0F0F0F0F0F0FULL) + ((x >> 4) & 0x0F0F0F0F0F0F0F0FULL);
    return (x * 0x0101010101010101ULL) >> 56;
}

////////////////////////////////////////////////////////////////

int to_binary(int x){

    int rem;
    int converted = 0;
    int multiplicator = 1;

    while (x > 0){

        rem = x % 2;
        x /= 2;
        converted += rem * multiplicator;
        multiplicator *= 10;
    }

    return converted;
}


/* This function calculates (ab)%c */
int modulo(int a,int b,int c){
    long long x=1,y=a; // long long is taken to avoid overflow of intermediate results
    while(b > 0){
        if(b%2 == 1){
            x=(x*y)%c;
        }
        y = (y*y)%c; // squaring the base
        b /= 2;
    }
    return x%c;
}


/* This function calculates nCr % MOD */
long long C(int n, int r, int MOD)
{
    vector< vector<long long> > C(2,vector<long long> (r+1,0));
 
    for (int i=0; i<=n; i++)
    {
        for (int k=0; k<=r && k<=i; k++)
            if (k==0 || k==i)
                C[i&1][k] = 1;
            else
                C[i&1][k] = (C[(i-1)&1][k-1] + C[(i-1)&1][k])%MOD;
    }
    return C[n&1][r];
}


 /* This function calculates x^n % MOD*/
long long power(long long x,long long n){
   
    if (n==0)
    return 1;
    if (n==1)
    return x%MOD;
    if (n%2==0)
    return (power((x*x)%MOD,n/2))%MOD;
    if (n%2!=0)
    return (x*power((x*x)%MOD,(n-1)/2))%MOD;

}

//faster and can be used upto n< 10^8
bool is_prime(int n) {

    if (n < 0) return is_prime(-n);
    if (n < 5 || n % 2 == 0 || n % 3 == 0)
    return (n == 2 || n == 3);
    int maxP = sqrt(n) + 2;
    for (int p = 5; p < maxP; p += 6)
    if (n % p == 0 || n % (p+2) == 0) return false;
    return true;

}

//sieve ...........................................

bool isPrime[ 5000005 ] ;

void seive(){

    settrue( isPrime ) ;      
    isPrime[ 0 ] = isPrime[ 1 ] = false ;
    
    for( int i = 2 ; i < 5000005 ; i++ ){
        
        if( isPrime[i] ){

            if(( i - 1 ) % 4 == 0 ) hypotenuse[ i ] = true ; 

            for( int j = 2*i ; j < 5000005 ; j+=i )
                isPrime[j] = false;
            
        }

    }

}

//................................................................



//This is also much faster than previous
const int SQRT_MAX_N = 10010;   // 
bool isPrime[SQRT_MAX_N];
vector<int> primes;
void seive(){
    
    memset(isPrime, 1, sizeof(isPrime));
    isPrime[0] = isPrime[1] = false;
    for(int i = 0; i < SQRT_MAX_N; i++)
    if(isPrime[i])
    {
        for(int j = 2*i; j < SQRT_MAX_N; j+=i)
            isPrime[j] = false;
        primes.push_back(i);

    }
}
 
bool ptest(int x){

    if(x < SQRT_MAX_N)
        return isPrime[x];
    for(int i = 0; i < sz(primes); i++)
        if(x%primes[i] == 0) return false;
    return true;

}
//to find primes , first call seive() in main,then just call ptest(number) number can be as large as SQRT_MAX_N^2 here 10^8;
//http://www.codechef.com/viewsolution/3638806      //to check the implementation


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Sort on second item in a list 

    bool myfunction(pair<int, int> firstElem, pair<int ,int> secondElem){
        
        return firstElem.second > secondElem.second;
    
    }

    int main(){

        vector<  pair<int , int>  > v;

        v.push_back( make_pair( 1 , 12 ) );
        v.push_back( make_pair( 1 , 1 ) );
        v.push_back( make_pair( 1 , 121 ) );
        v.push_back( make_pair( 1 , -1 ) ) ;   

        //descending wala hai yeh
        sort( v.begin() , v.end() , myfunction ) ;

        for(int i=0;i<4;i++){

            cout<<v[i].first<<" "<<v[i].second<<endl;

        }


    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// modular inverse( ( a / b )%m ) only exists when b and m are both prime and also co-prime to each other but what if b and m 
// are having GCD( b  , m ) == 1 ie they are relatively prime to each other but still either b/m or both are not prime 
// eg if(a / 3)%41 is to be calculated then (3^39)%41 will give u mod inverse but if( a/ 4 )%9 is to be calculated then ?

// hence in such situation we use extended euler algorithm

    long long extGcd(long long a, long long b, long long &x, long long &y){
        
        if(b == 0){
            
            x = 1;
            y = 0;
            return a;

        }

        long long g = extGcd(b,a % b,y,x);
        y -= a / b * x;
        return g;

    }
    long long modInv(long long a, long long m){
        
        long long x,y;
        extGcd(a, m, x, y);
        return (x % m + m) % m;

    }

//  if both denominator and mod are not relatively prime then ??
//  eg if we were to calculate ( a/4 )%24 then in such cases we use chinese remainder theoram 

    long long extGcd(long long a, long long b, long long &x, long long &y){
        
        if(b == 0){
            
            x = 1;
            y = 0;
            return a;

        }

        long long g = extGcd(b,a % b,y,x);
        y -= a / b * x;
        return g;

    }
    long long modInv(long long a, long long m){
        
        long long x,y;
        extGcd(a, m, x, y);
        return (x % m + m) % m;

    }
    long long chinese_remainder2(LL *mod, LL *rem, int count ){

        long long ans = rem[0],m = mod[0];
        for(int i = 1;i < count ;++i){

            LL a = modInv(m,mod[i]);
            LL b = modInv(mod[i],m);
            ans = (ans * b * mod[i] + (long long)rem[i] * a * m) % (m * mod[i]);
            m *= mod[i];

        }

        return ans;

    }

    // refer Fombinatorial problem on codechef http://www.codechef.com/NOV14/status/POWERMUL,sunnyshah28
/////

// BIgInteger Library refer the warmup round contest on codechef submission http://www.codechef.com/viewsolution/6317584

    long long BIG=1000000000000000000LL;

    // base and base_digits must be consistent
    const int base = 1000000000;
    const int base_digits = 9;

    struct bigint {
        vector<int> a;
        int sign;

        bigint() :
            sign(1) {
        }

        bigint(long long v) {
            *this = v;
        }

        bigint(const string &s) {
            read(s);
        }

        void operator=(const bigint &v) {
            sign = v.sign;
            a = v.a;
        }

        void operator=(long long v) {
            sign = 1;
            if (v < 0)
                sign = -1, v = -v;
            for (; v > 0; v = v / base)
                a.push_back(v % base);
        }

        bigint operator+(const bigint &v) const {
            if (sign == v.sign) {
                bigint res = v;

                for (int i = 0, carry = 0; i < (int) max(a.size(), v.a.size()) || carry; ++i) {
                    if (i == (int) res.a.size())
                        res.a.push_back(0);
                    res.a[i] += carry + (i < (int) a.size() ? a[i] : 0);
                    carry = res.a[i] >= base;
                    if (carry)
                        res.a[i] -= base;
                }
                return res;
            }
            return *this - (-v);
        }

        bigint operator-(const bigint &v) const {
            if (sign == v.sign) {
                if (abs() >= v.abs()) {
                    bigint res = *this;
                    for (int i = 0, carry = 0; i < (int) v.a.size() || carry; ++i) {
                        res.a[i] -= carry + (i < (int) v.a.size() ? v.a[i] : 0);
                        carry = res.a[i] < 0;
                        if (carry)
                            res.a[i] += base;
                    }
                    res.trim();
                    return res;
                }
                return -(v - *this);
            }
            return *this + (-v);
        }

        void operator*=(int v) {
            if (v < 0)
                sign = -sign, v = -v;
            for (int i = 0, carry = 0; i < (int) a.size() || carry; ++i) {
                if (i == (int) a.size())
                    a.push_back(0);
                long long cur = a[i] * (long long) v + carry;
                carry = (int) (cur / base);
                a[i] = (int) (cur % base);
                //asm("divl %%ecx" : "=a"(carry), "=d"(a[i]) : "A"(cur), "c"(base));
            }
            trim();
        }

        bigint operator*(int v) const {
            bigint res = *this;
            res *= v;
            return res;
        }

        friend pair<bigint, bigint> divmod(const bigint &a1, const bigint &b1) {
            int norm = base / (b1.a.back() + 1);
            bigint a = a1.abs() * norm;
            bigint b = b1.abs() * norm;
            bigint q, r;
            q.a.resize(a.a.size());

            for (int i = a.a.size() - 1; i >= 0; i--) {
                r *= base;
                r += a.a[i];
                int s1 = r.a.size() <= b.a.size() ? 0 : r.a[b.a.size()];
                int s2 = r.a.size() <= b.a.size() - 1 ? 0 : r.a[b.a.size() - 1];
                int d = ((long long) base * s1 + s2) / b.a.back();
                r -= b * d;
                while (r < 0)
                    r += b, --d;
                q.a[i] = d;
            }

            q.sign = a1.sign * b1.sign;
            r.sign = a1.sign;
            q.trim();
            r.trim();
            return make_pair(q, r / norm);
        }

        bigint operator/(const bigint &v) const {
            return divmod(*this, v).first;
        }

        bigint operator%(const bigint &v) const {
            return divmod(*this, v).second;
        }

        void operator/=(int v) {
            if (v < 0)
                sign = -sign, v = -v;
            for (int i = (int) a.size() - 1, rem = 0; i >= 0; --i) {
                long long cur = a[i] + rem * (long long) base;
                a[i] = (int) (cur / v);
                rem = (int) (cur % v);
            }
            trim();
        }

        bigint operator/(int v) const {
            bigint res = *this;
            res /= v;
            return res;
        }

        int operator%(int v) const {
            if (v < 0)
                v = -v;
            int m = 0;
            for (int i = a.size() - 1; i >= 0; --i)
                m = (a[i] + m * (long long) base) % v;
            return m * sign;
        }

        void operator+=(const bigint &v) {
            *this = *this + v;
        }
        void operator-=(const bigint &v) {
            *this = *this - v;
        }
        void operator*=(const bigint &v) {
            *this = *this * v;
        }
        void operator/=(const bigint &v) {
            *this = *this / v;
        }

        bool operator<(const bigint &v) const {
            if (sign != v.sign)
                return sign < v.sign;
            if (a.size() != v.a.size())
                return a.size() * sign < v.a.size() * v.sign;
            for (int i = a.size() - 1; i >= 0; i--)
                if (a[i] != v.a[i])
                    return a[i] * sign < v.a[i] * sign;
            return false;
        }

        bool operator>(const bigint &v) const {
            return v < *this;
        }
        bool operator<=(const bigint &v) const {
            return !(v < *this);
        }
        bool operator>=(const bigint &v) const {
            return !(*this < v);
        }
        bool operator==(const bigint &v) const {
            return !(*this < v) && !(v < *this);
        }
        bool operator!=(const bigint &v) const {
            return *this < v || v < *this;
        }

        void trim() {
            while (!a.empty() && !a.back())
                a.pop_back();
            if (a.empty())
                sign = 1;
        }

        bool isZero() const {
            return a.empty() || (a.size() == 1 && !a[0]);
        }

        bigint operator-() const {
            bigint res = *this;
            res.sign = -sign;
            return res;
        }

        bigint abs() const {
            bigint res = *this;
            res.sign *= res.sign;
            return res;
        }

        long long longValue() const {
            long long res = 0;
            for (int i = a.size() - 1; i >= 0; i--)
                res = res * base + a[i];
            return res * sign;
        }

        friend bigint gcd(const bigint &a, const bigint &b) {
            return b.isZero() ? a : gcd(b, a % b);
        }
        friend bigint lcm(const bigint &a, const bigint &b) {
            return a / gcd(a, b) * b;
        }

        void read(const string &s) {
            sign = 1;
            a.clear();
            int pos = 0;
            while (pos < (int) s.size() && (s[pos] == '-' || s[pos] == '+')) {
                if (s[pos] == '-')
                    sign = -sign;
                ++pos;
            }
            for (int i = s.size() - 1; i >= pos; i -= base_digits) {
                int x = 0;
                for (int j = max(pos, i - base_digits + 1); j <= i; j++)
                    x = x * 10 + s[j] - '0';
                a.push_back(x);
            }
            trim();
        }

        friend istream& operator>>(istream &stream, bigint &v) {
            string s;
            stream >> s;
            v.read(s);
            return stream;
        }

        friend ostream& operator<<(ostream &stream, const bigint &v) {
            if (v.sign == -1)
                stream << '-';
            stream << (v.a.empty() ? 0 : v.a.back());
            for (int i = (int) v.a.size() - 2; i >= 0; --i)
                stream << setw(base_digits) << setfill('0') << v.a[i];
            return stream;
        }

        static vector<int> convert_base(const vector<int> &a, int old_digits, int new_digits) {
            vector<long long> p(max(old_digits, new_digits) + 1);
            p[0] = 1;
            for (int i = 1; i < (int) p.size(); i++)
                p[i] = p[i - 1] * 10;
            vector<int> res;
            long long cur = 0;
            int cur_digits = 0;
            for (int i = 0; i < (int) a.size(); i++) {
                cur += a[i] * p[cur_digits];
                cur_digits += old_digits;
                while (cur_digits >= new_digits) {
                    res.push_back(int(cur % p[new_digits]));
                    cur /= p[new_digits];
                    cur_digits -= new_digits;
                }
            }
            res.push_back((int) cur);
            while (!res.empty() && !res.back())
                res.pop_back();
            return res;
        }

        typedef vector<long long> vll;

        static vll karatsubaMultiply(const vll &a, const vll &b) {
            int n = a.size();
            vll res(n + n);
            if (n <= 32) {
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        res[i + j] += a[i] * b[j];
                return res;
            }

            int k = n >> 1;
            vll a1(a.begin(), a.begin() + k);
            vll a2(a.begin() + k, a.end());
            vll b1(b.begin(), b.begin() + k);
            vll b2(b.begin() + k, b.end());

            vll a1b1 = karatsubaMultiply(a1, b1);
            vll a2b2 = karatsubaMultiply(a2, b2);

            for (int i = 0; i < k; i++)
                a2[i] += a1[i];
            for (int i = 0; i < k; i++)
                b2[i] += b1[i];

            vll r = karatsubaMultiply(a2, b2);
            for (int i = 0; i < (int) a1b1.size(); i++)
                r[i] -= a1b1[i];
            for (int i = 0; i < (int) a2b2.size(); i++)
                r[i] -= a2b2[i];

            for (int i = 0; i < (int) r.size(); i++)
                res[i + k] += r[i];
            for (int i = 0; i < (int) a1b1.size(); i++)
                res[i] += a1b1[i];
            for (int i = 0; i < (int) a2b2.size(); i++)
                res[i + n] += a2b2[i];
            return res;
        }

        bigint operator*(const bigint &v) const {
            vector<int> a6 = convert_base(this->a, base_digits, 6);
            vector<int> b6 = convert_base(v.a, base_digits, 6);
            vll a(a6.begin(), a6.end());
            vll b(b6.begin(), b6.end());
            while (a.size() < b.size())
                a.push_back(0);
            while (b.size() < a.size())
                b.push_back(0);
            while (a.size() & (a.size() - 1))
                a.push_back(0), b.push_back(0);
            vll c = karatsubaMultiply(a, b);
            bigint res;
            res.sign = sign * v.sign;
            for (int i = 0, carry = 0; i < (int) c.size(); i++) {
                long long cur = c[i] + carry;
                res.a.push_back((int) (cur % 1000000));
                carry = (int) (cur / 1000000);
            }
            res.a = convert_base(res.a, 6, base_digits);
            res.trim();
            return res;
        }
    };
    
    int main(){

        string a , b ; 
        cin>>a ; 
        cin>>b ; 
        bigint n1 = a , n2 = b ;
        bigint n3 = n1 + n2 ;
        cout<<"Result = " ;
    
        cout<<n3 ;  
        
        return 0 ; 

    }
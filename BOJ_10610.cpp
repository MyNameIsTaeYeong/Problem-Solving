#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;


int main()
{
	string n;
	cin >> n;

	vector<char> v;


	bool zero_check = false;
	int three_check = 0;

	for (int i = 0; i < n.size(); i++) {
		v.push_back(n[i]);
		three_check += n[i] - '0';
		if (n[i] == '0')
			zero_check = true;
	}
	//0이 있는지(2의 배수 & 5의 배수)
	if (!zero_check) {
		cout << -1;
		return 0;
	}
	//각 수의 합이 3의 배수인지(3의 배수)
	if (three_check % 3 != 0) {
		cout << -1;
		return 0;
	}
	//위 조건을 만족하는 수중 가장 큰 수
	sort(v.begin(), v.end());
	reverse(v.begin(), v.end());

	for (auto it = v.begin(); it < v.end(); it++)
		cout << *it;

	return 0;
}
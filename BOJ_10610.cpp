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
	//0�� �ִ���(2�� ��� & 5�� ���)
	if (!zero_check) {
		cout << -1;
		return 0;
	}
	//�� ���� ���� 3�� �������(3�� ���)
	if (three_check % 3 != 0) {
		cout << -1;
		return 0;
	}
	//�� ������ �����ϴ� ���� ���� ū ��
	sort(v.begin(), v.end());
	reverse(v.begin(), v.end());

	for (auto it = v.begin(); it < v.end(); it++)
		cout << *it;

	return 0;
}
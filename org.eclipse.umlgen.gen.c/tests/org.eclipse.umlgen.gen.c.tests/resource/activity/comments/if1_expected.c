printf("A");

// Before 1
int i = 10;
// Before 2
if (/*inline 1*/ i < 5) { // Same line 1
	// Inside then clause
	printf("C");
	// Last line of then clause
}

printf("B");
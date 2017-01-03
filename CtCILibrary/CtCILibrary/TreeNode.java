package CtCILibrary;

/* One node of a binary tree. The data element stored is a single 
 * character.
 */
public class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	private int size=0;

	public TreeNode(int d) {
		this.data = d;
		this.size = 1;
	}
	// 자식 노드 생성
	public void setLeftChild(TreeNode left){
		this.left = left;
		if(left !=null){
			left.parent = this;
		}
	}

	public void setRightChild(TreeNode right){
		this.right = right;
		if(right != null) {
			right.parent = this;
		}
	}

	public void insertInOrder(int data){
		// 데이터가 노드에 자리잡고 있는 넘보다 작고,
		if (data <= this.data){
			// 터미널 노드이면,
			if(left ==null) {
				// 왼쪽에 자식노드 객체 생성
				setLeftChild(new TreeNode(data));
			} else {    // 터미널 노드가 아니면,
				// 재귀함수 호출. 왼쪽 자식노드에 "대소구분하여 자식노드 추가하는 함수" 다시 재귀호출
				this.left.insertInOrder(data);
			}
		}
		else { // 데이터 노드에 자리잡고 ᅟ있는 넘보다 크고, (오른쪽 노드 처리고 가겠지..)
			// 터미널노드이면,
			if (right == null) {
				// 오른쪽 자식노드 객체 생성
				setRightChild((new TreeNode(data)));
			} else {    // 터미널 노드가 아니면
				// 재귀 함수 호출, 오른쪽 자식노드에 "대소구분하여 자식노드 추가하는 함수" 루틴 재진입.
				this.right.insertInOrder(data);
			}
		}
		// 다 끝났으면 (주어진 데이터 값에 대해 노드의 어디엔가 삽입이 끝났으면,
		size++; 	// Node 개수 하나 더 증가요....
	}

	//getter
	public int size(){
		return size;
	}

	// 균형 트리인지 판별하는 함수.
	public boolean isBST(){
		// 터미널 노드인지부터 언제나 판단하는구만....
		if(this.left !=null)  // 터미널 노드가 아닐 경우에만 판단해보자구요.
			// 재귀적으로다가 터미널 노드 오면 추가 재귀 호출 금지의 목적...
		{
			if(this.data < left.data || ! left.isBST())
				// 지금 현재노드의 데이터가 왼쪽 자식노드 데이터
				// 값보다 작으면 균형이진트리가 아니지......
				// OR
				// 리커시브 또 들어감. 왼쪽 자식 넘 기준으로 또 손자 녀석과 비교해보는거지.
				// 그렇게 왼쪽 애덜 재귀함수로 쭈욱 TRAVERSE
				return  false;	// 맞는 조건 생기면 얘는 글렀어 더이상 재귀 탐험 끝하고 거짓 리턴
		}

		ᅟ// 이제 오른쪽 자식 후손들 쭈욱 살펴볼까?
		if (this.right != null)  // 터미널 노드까지 왔으면 (재귀 예상) 루프 종료 의미
		{
			if (this.data > right.data || ! right.isBST())
				return false;
		}

		// 왼쪽 노드 오른쪽 노드 철저하게 다봤는데 false 를 리턴 안한상태야? 그럼 so clear
		return	true;

	}

	// 트리 높이 구하는 함수
	public int height(){
		int leftHeight  = this.left  != null ? this.left.height() : 0; // 종단 노드까지 쭈욱 가봐....
		ᅟint rightHeight = this.right != null ? this.right.height(): 0;
		return 1+ Math.max(leftHeight, rightHeight);
		// 한번 리커시브 돌 때마다 1씩 더해진 격.
	}

	// BST (균형 이진 트리) 에서 특정 값 찾기
	public TreeNode find(int data){
		// 어라 root 에 벌써 원하는 값이 있네. 맨 꼭 대기에 있었네 ( 중간값)
		if (data == this.data) {
			return this;
		} else if ( data <= this.data) {	// 원하는 값이랑 노드랑 비교했더니, 노드보다 작네. 왼쪽으로 가야지
			return this.left !=null ? this.left.find(data) : null;					// null 을 만날때까지 종단노드까지... 리커시
		} else if( data > this.data) { // 원하는값 노드 비교 값이 크네? 오른쪽 자식으로 쭈욱 가봐야지.
			return this.right !=null ? this.right.find(data) : null; // 지가 알아서 막 갈꺼야. 트레버스 / 이렇게 정의해놓으면...
		}  // 표현식.. 정의 recursive 정의형......로직....
		return null;
	}	//  재귀함수 참 심오하네... 인공지능같애.....

	// Polymorphism 다형성...  시작 끝을 정했을 때와 그렇지않을때. 두개가 또 엮여있네...
	private static TreeNode createMinimalBST(int arr[], int start, int end){
		if (end <start) {    // 재귀 호출 하려고 폼잡는거
			return null;
		}
		int mid = (start + end ) /2;
		TreeNode tn = new TreeNode(arr[mid]); // root에서 갈라질 준비
		tn.setLeftChild(createMinimalBST(arr, start, mid-1)); // left node 쭈욱 만들어보자
		tn.setRightChild(createMinimalBST(arr, mid+1, end));
		return tn;
	}  // 정의형에 익숙해지자....

	public static  TreeNode createMinimalBST(int array[]){
		return createMinimalBST(array,0, array.length-1);
	}

	public void print(){
		BTreePrinter.printNode(this);
	}



}

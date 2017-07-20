# MokitoTest
* Mokito là thư viện dùng để thực thi Junit test, tương tự như Junit của Android. Thư viện này sử dụng các đối tượng ảo để thực hiện test. Điều này đồng nghĩa với việc chúng ta không cần sử dụng điện thoại hoặc máy ảo Android để test. Hay nói cách khác, các đối tượng liên quan đến nền tảng Android của thiết bị sẽ không được tạo ảo một cách dễ dàng (nên tránh tạo ảo các đối tượng này).
* Để tạo được đối tượng ảo, chúng ta cần quan tâm đến 2 đối tượng là mock object (`@Mock`) và spy object (`@Spy`)
## @Mock
Anotation trên được dùng để tạo một đối tượng thuần ảo. Một đối tượng thuần ảo nghĩa là không thực hiện được các method mà đối tượng thực có thể thực hiện được, mà thay vào đấy chúng ta phải **stub** giá trị trước cho nó. Ví dụ như sau:
```
public class User{
 private String mName;
 public String createName(String name){
    mName = name;
 }
}
```
Đối tượng thực User trên có method `createName(String name)` nhưng nếu ta tạo 
```
@Mock
User user;
```

thì khi thực hiện `user.createName(name)` sẽ mặc định cho ra kết quả là null, đồng nghĩa với việc chúng ta không test được hàm này. Vậy làm sao chúng ta có thể tiến hành test được hàm như mục đích ban đầu?
Có 2 cách để tiếp tục test. Cách thứ nhất là thay vì tạo mock object thì chúng ta sẽ tạo spy object (được nói đến ở mục sau). Cách thứ 2 là như đã nói ở phần mở đầu, tiến hành gán giá trị trước cho nó để đối tượng mock có thể trả về được giá trị như sau:
```
@Mock
User user
@Test
public void test{
 when(user.create("Dung")).thenReturn("Dung");
 /**
  * Bây giờ, khi gọi hàm createName, sẽ trả vể giá trị Dung
  */
  System.out.println(user.create("Dung"));
}
```
Kết quả: `Dung`

Việc tạo đối tượng mock hoàn toàn thực hiện được cho interface, class có constructor hoặc không có constructor. Ngoài ra, ngoài tạo đối tượng mock bằng anotation, chúng ta có thể tạo đối tượng mock bằng câu lệnh sau:

`User user = mock(new User());`
## @Spy
Tương tự như câu lệnh tạo đối tượng thuần ảo ở trên thì 
```
 @Spy
 User user;
```
được dùng để tạo một đối tượng spy. Đối tượng spy là đối tượng bán ảo, hay nói cách khác nó vừa là đối tượng thực, vừa là đối tượng ảo. Vừa là đối tượng thực vì nó hoàn toàn có thể thực hiện các method của một đối tượng thực một cách chính xác, không cần **stub** trước giá trị để trả về như đối tượng mock. Vừa là đối tượng ảo vì nó có thể thực hiện các câu lệnh của một đối tượng mock.
 Quay trở lại với ví dụ trên, nếu sử dụng đối tượng spy, ta thực hiện như sau
 ```
 @Spy 
 User user;
 @Test
public void test{
 /**
  * Thực hiện luôn hàm createName mà không cần stub trước giá trị
  */
  System.out.println(user.create("Dung"));
} 
```
 Kết quả: `Dung`
 
 Để tạo được đối tượng spy, ngoài anotation, chúng ta cũng có thể thực hiện được thông qua câu lệnh
 
 `User user = spy(new User);`
 ## Tiến hành test các chức năng mà mình muốn sau khi đã tạo xong đối tượng mock hoặc spy
  Để có thể thực hiện test, chúng ta cần xác định chức năng mình muốn test để từ đó xác định các câu lệnh phù hợp. 
  ### doAnswer...when()
   Ví dụ khi chúng ta gặp một hàm `void`, được gọi trong một hàm khác. Và nhiệm vụ của chúng ta là phải giả thiết hàm void phải trả về     một giá trị nào đấy (thông thường trong hàm này sẽ viết callback), thì chúng ta có thể `stub` giá trị thông qua việc override lại hàm   trong interface `Answer`
   
    doAnswer(new Answer() {
        public Object answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            return null;
        }})
     .when(user).someMethodVoid();
     
   ### Câu lệnh verify
     Câu lệnh
     
     
     ```
     verify(mockObject).someMethod();
     ``` 
     dùng để xác thực một đối tượng mockObject có thực thi hàm someMethod() lần nào hay không.
    
   ### Sử dụng các câu lệnh của Junit kết hợp để test
     Vốn dĩ trong android đã có sẵn bộ thư viện Junit dùng để test cho unitTest và instrumentTest, nay chúng ta lại sử dụng thêm bộ thư     viện thứ 3 là mockito test để thực hiện unitTest. Junit có các hàm để test rất mạnh nằm trong lớp `Assert` như `Assert.assertEqual()`,`Assert.assert.NotNull()`,`Assert.assertEqualArray()`...          
    Chúng ta hoàn toàn có thể sử dụng kết hợp các hàm trên để test.

package tk.xmfaly.zhihu_server.dto;

import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> {

    @NonNull
    private int code;
    @NonNull
    private String msg;
    private T data;

}

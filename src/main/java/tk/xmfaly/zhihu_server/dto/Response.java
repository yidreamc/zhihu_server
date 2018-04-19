package tk.xmfaly.zhihu_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Response<T> {

    @NonNull
    private int code;
    @NonNull
    private String msg;
    private T data;
}

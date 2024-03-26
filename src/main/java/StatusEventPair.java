import java.util.Objects;

/**
 * 状态-事件对
 *
 */
public class StatusEventPair<S extends BaseStatus, E extends BaseEvent> {

    /**
     * 指定的状态
     */
    private final S status;

    /**
     * 可接受的事件
     */

    private final E event;

    /**
     * 构造函数
     */
    public StatusEventPair(S status, E event) {
        this.status = status;
        this.event = event;
    }

    // equals 方法
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatusEventPair) {
            StatusEventPair<S, E> other = (StatusEventPair<S, E>)obj;
            if (this.status == null || this.event == null) {
                return this.event.equals(other.event);
            }
            return this.status.equals(other.status) && this.event.equals(other.event);
        }
        return false;
    }

    // hashCode 方法
    @Override
    public int hashCode() {
        return Objects.hash(status, event);
    }
}

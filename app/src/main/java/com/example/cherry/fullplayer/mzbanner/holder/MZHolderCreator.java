package com.example.cherry.fullplayer.mzbanner.holder;

import com.zhouwei.mzbanner.holder.*;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface MZHolderCreator<VH extends com.zhouwei.mzbanner.holder.MZViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}

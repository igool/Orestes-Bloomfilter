package orestes.bloomfilter.memory;


import orestes.bloomfilter.FilterBuilder;

import java.util.Objects;

public class CountingBloomFilter32<T> extends CountingBloomFilterMemory<T>{
    private int[] counters;

    public CountingBloomFilter32(FilterBuilder config) {
        config.complete();
        this.config = config;
        this.filter = new BloomFilterMemory<>(config.clone());
        this.counters = new int[config.size()];
    }

    @Override
    protected long increment(int index) {
        return Integer.toUnsignedLong(++counters[index]);
    }

    @Override
    protected long decrement(int index) {
        return Integer.toUnsignedLong(--counters[index]);
    }

    @Override
    protected long count(int index) {
        return Integer.toUnsignedLong(counters[index]);
    }

    @Override
    public void clear() {
        filter.clear();
        this.counters = new int[counters.length];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof CountingBloomFilter32)) { return false; }
        if (!super.equals(o)) { return false; }
        CountingBloomFilter32<?> that = (CountingBloomFilter32<?>) o;
        return Objects.equals(counters, that.counters);
    }

}

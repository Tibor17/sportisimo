package com.sportisimo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
public class AbstractEntity implements Serializable {
    @SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ID_SEQUENCE_GENERATOR", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "ID_SEQUENCE_GENERATOR", strategy = SEQUENCE)
    @Column(name = "ID", precision = 19)
    private Long id;

    @Column(name = "VERSION_ID", nullable = false)
    @Version
    private long versionId;

    public Long getId() {
        return id;
    }

    @Transient
    public boolean equals(Object e) {
        return e == this || e != null
                && getClass().isAssignableFrom(e.getClass())
                && Objects.equals(id, ((AbstractEntity) e).getId());
    }

    @Transient
    public int hashCode() {
        final Number id = this.id;
        // No need to cache a trivial hash-code on this instance, reasons with fast runtime compiler:
        // The Just In Time compiler of HotSpot VM compiles this method to the native code immediately
        // and the method Integer#hashCode() to the native code as well
        // and inlines hashCode==number value directly into this method stack because sizeOf this method in bytecode < 32 bytes.
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

    @Transient
    public final boolean hasId() {
        Number id = getId();
        return id != null && id.longValue() != 0;
    }

    @Transient
    public final boolean isNew() {
        return !hasId();
    }


    @Transient
    public String toString() {
        return getClass().getSimpleName() + "#" + id;
    }

    public long getVersionId() {
        return versionId;
    }
}
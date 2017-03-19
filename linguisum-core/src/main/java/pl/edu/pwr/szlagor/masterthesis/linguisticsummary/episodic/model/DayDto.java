package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Pawel on 2017-02-08.
 */
public class DayDto {

    private List<Snapshot> snapshots;

    @java.beans.ConstructorProperties({ "snapshots" })
    public DayDto(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public DayDto() {
    }

    public static DayDtoBuilder builder() {
        return new DayDtoBuilder();
    }

    public List<Snapshot> getSnapshots() {
        return this.snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DayDto))
            return false;
        final DayDto other = (DayDto) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$snapshots = this.getSnapshots();
        final Object other$snapshots = other.getSnapshots();
        if (this$snapshots == null ? other$snapshots != null : !this$snapshots.equals(other$snapshots))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $snapshots = this.getSnapshots();
        result = result * PRIME + ($snapshots == null ? 43 : $snapshots.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof DayDto;
    }

    public static class DayDtoBuilder {
        private ArrayList<Snapshot> snapshots;

        DayDtoBuilder() {
        }

        public DayDto.DayDtoBuilder snapshot(Snapshot snapshot) {
            if (this.snapshots == null)
                this.snapshots = new ArrayList<Snapshot>();
            this.snapshots.add(snapshot);
            return this;
        }

        public DayDto.DayDtoBuilder snapshots(Collection<? extends Snapshot> snapshots) {
            if (this.snapshots == null)
                this.snapshots = new ArrayList<Snapshot>();
            this.snapshots.addAll(snapshots);
            return this;
        }

        public DayDto.DayDtoBuilder clearSnapshots() {
            if (this.snapshots != null)
                this.snapshots.clear();

            return this;
        }

        public DayDto build() {
            List<Snapshot> snapshots;
            switch (this.snapshots == null ? 0 : this.snapshots.size()) {
                case 0:
                    snapshots = java.util.Collections.emptyList();
                    break;
                case 1:
                    snapshots = java.util.Collections.singletonList(this.snapshots.get(0));
                    break;
                default:
                    snapshots = java.util.Collections.unmodifiableList(new ArrayList<Snapshot>(this.snapshots));
            }

            return new DayDto(snapshots);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DayDto.DayDtoBuilder(snapshots=" + this.snapshots
                    + ")";
        }
    }
}

package nabla.model.collision;

import nabla.model.world.Map;
import nabla.model.entity.IEntity;
import nabla.util.Direction;
import nabla.util.Position;

import java.util.List;

/**
 * Collision checker untuk mengecek apakah suatu posisi dapat dilalui.
 * <p>
 * Menyediakan aturan umum untuk block penghalang, batas map, dan entity lain
 * yang menghalangi pergerakan. Strategy pattern menggunakan class ini
 * untuk memutuskan apakah gerakan diperbolehkan.
 */
public class CollisionChecker {
    private final Map map;
    private final List<IEntity> blockers;

    public CollisionChecker(Map map, List<IEntity> blockers) {
        this.map = map;
        this.blockers = blockers;
    }

    /**
     * Cek apakah pergerakan dari posisi saat ini ke arah yang diberikan diperbolehkan.
     */
    public boolean canMove(Position current, Direction direction) {
        Position target = direction.deltaPosition().add(current);

        /* Cek batas map agar tidak terjadi undefined behavior */
        if (target.getRow() < 0 || target.getRow() >= map.getHeight()
                || target.getCol() < 0 || target.getCol() >= map.getWidth()) {
            return false;
        }

        /* Cek block penghalang */
        if (map.getBlocks()[target.getRow()][target.getCol()] != null) {
            return false;
        }

        /* Cek entity lain yang menghalangi */
        for (IEntity entity: blockers) {
            if (entity.isActive() && entity.blocksMovement() && entity.getPosition().isSameTile(target)) {
                return false;
            }
        }

        return true;
    }

    /* TODO coba abstraksi agar kode di bawah ini bisa dihapus */

    /**
     * Cek apakah suatu posisi adalah tile Ice.
     */
    public boolean isIceTile(Position pos) {
        if (pos.getRow() < 0 || pos.getRow() >= map.getHeight()
                || pos.getCol() < 0 || pos.getCol() >= map.getWidth()) {
            return false;
        }
        return map.getTiles()[pos.getRow()][pos.getCol()] == nabla.type.TileType.ICE;
    }

    /**
     * Cek apakah suatu posisi adalah tile Water.
     */
    public boolean isWaterTile(Position pos) {
        if (pos.getRow() < 0 || pos.getRow() >= map.getHeight()
                || pos.getCol() < 0 || pos.getCol() >= map.getWidth()) {
            return false;
        }
        return map.getTiles()[pos.getRow()][pos.getCol()] == nabla.type.TileType.WATER;
    }
}

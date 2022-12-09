package y2021.day23;

import common.Point;
import common.PointUtils;

import java.util.*;
import java.util.stream.Collectors;

import static common.Point.p;

//Does not work
public class Day23_1bis {

    public static final Set<Room> ROOMS = Set.of(
            Room.room(p(0, 0), "."),
            Room.room(p(1, 0), "."),
            Room.room(p(2, 0), "#"),
            Room.room(p(3, 0), "."),
            Room.room(p(4, 0), "#"),
            Room.room(p(5, 0), "."),
            Room.room(p(6, 0), "#"),
            Room.room(p(7, 0), "."),
            Room.room(p(8, 0), "#"),
            Room.room(p(9, 0), "."),
            Room.room(p(10, 0), "."),
            Room.room(p(2, 1), "A"),
            Room.room(p(2, 2), "A"),
            Room.room(p(2, 3), "A"),
            Room.room(p(2, 4), "A"),
            Room.room(p(4, 1), "B"),
            Room.room(p(4, 2), "B"),
            Room.room(p(4, 3), "B"),
            Room.room(p(4, 4), "B"),
            Room.room(p(6, 1), "C"),
            Room.room(p(6, 2), "C"),
            Room.room(p(6, 3), "C"),
            Room.room(p(6, 4), "C"),
            Room.room(p(8, 1), "D"),
            Room.room(p(8, 2), "D"),
            Room.room(p(8, 3), "D"),
            Room.room(p(8, 4), "D")
    );

    public static final String INPUT = """
            #############
            #...........#
            ###D#D#B#A###
              #D#C#B#A#
              #D#B#A#C#
              #B#C#A#C#
              #########""";

    public static final Set<Entity> STARTING_STATE = Set.of(
            Entity.e(p(2, 1), "D"),
            Entity.e(p(2, 2), "D"),
            Entity.e(p(2, 3), "D"),
            Entity.e(p(2, 4), "B"),
            Entity.e(p(4, 1), "D"),
            Entity.e(p(4, 2), "C"),
            Entity.e(p(4, 3), "B"),
            Entity.e(p(4, 4), "C"),
            Entity.e(p(6, 1), "B"),
            Entity.e(p(6, 2), "B"),
            Entity.e(p(6, 3), "A"),
            Entity.e(p(6, 4), "A"),
            Entity.e(p(8, 1), "A"),
            Entity.e(p(8, 2), "A"),
            Entity.e(p(8, 3), "C"),
            Entity.e(p(8, 4), "C")
    );

    public static final Set<Entity> FINAL_STATE = Set.of(
            Entity.e(p(2, 1), "A"),
            Entity.e(p(2, 2), "A"),
            Entity.e(p(2, 3), "A"),
            Entity.e(p(2, 4), "A"),
            Entity.e(p(4, 1), "B"),
            Entity.e(p(4, 2), "B"),
            Entity.e(p(4, 3), "B"),
            Entity.e(p(4, 4), "B"),
            Entity.e(p(6, 1), "C"),
            Entity.e(p(6, 2), "C"),
            Entity.e(p(6, 3), "C"),
            Entity.e(p(6, 4), "C"),
            Entity.e(p(8, 1), "D"),
            Entity.e(p(8, 2), "D"),
            Entity.e(p(8, 3), "D"),
            Entity.e(p(8, 4), "D")
    );

    public static final Map<Point, Room> ROOMS_BY_POS = new HashMap<>();

    static {
        ROOMS.forEach(room -> ROOMS_BY_POS.put(room.position(), room));
    }

    public static Map<String, List<Point>> ROOMS_BY_TYPE = ROOMS.stream()
            .collect(Collectors.groupingBy(Room::type,
                                           Collectors.mapping(Room::position, Collectors.toList())));

    public static final Map<Room, List<Room>> NEIGHBOURS = new HashMap<>();

    static {
        ROOMS.forEach(room -> {
            final Set<Point> points = PointUtils.computeOrthogonalNeighbours(room.position(), Integer.MAX_VALUE,
                                                                             Integer.MAX_VALUE);
            NEIGHBOURS.put(room,
                           points.stream()
                                   .map(ROOMS_BY_POS::get)
                                   .filter(Objects::nonNull)
                                   .toList());
        });
    }

    public static final Map<String, Integer> ENTITY_MOVE_POINT = Map.of(
            "A", 1,
            "B", 10,
            "C", 100,
            "D", 1000
    );

    public static void main(String[] args) {
        System.out.println(toString(ROOMS, "\n"));
        System.out.println(toString(ROOMS_BY_POS, "\n"));
        System.out.println(toString(NEIGHBOURS, "\n"));

        recursive(STARTING_STATE);
    }

    static Map<Set<Entity>, Long> CACHE = new HashMap<>();

    private static long recursive(final Set<Entity> entities) {
        //Si pos final = valid return score
        //Si on est dans l'état final, le cout = 0;
        if (isFinal(entities)) {
            return 0;
        }

        if (CACHE.containsKey(entities))
            return CACHE.get(entities);


        //Chaque entité ne peut faire que 2 mouvements maximum :
        //Un mouvement pour sortir de sa colonne de départ un mouvement pour aller dans sa colonne d'arrivée

        //Pour chaque entité dans le hall, on essaie de la faire aller dans sa colonne à la position la plus loin
        //Si on peut : recursion, sinon next

        final List<Entity> inHall = inHallEntities(entities);
        for (Entity entity : inHall) {
            Point newPosition = findAvailablePositionInRoom(entity, entities);
            if (newPosition == null) {
                continue;
            }
            final Set<Entity> newState = new HashSet<>(entities);
            newState.remove(entity);
            newState.add(new Entity(newPosition, entity.type()));

            return cost(entity, newPosition) + recursive(newState);
        }

        //Si aucune entité n'a pu être placée dans sa colonne d'arrivée :
        //Reinit le min
        //Pour chaque entité :
        //  Si elle peut être déplacée de sa colonne (ie : il y a encore des entités de type =/= dans la colonne)
        //  Pour chaque place dispo dans le hall
        //    Si la position est accessible
        //      Créer le nouvel état
        //      ans = min(ans, cost + recursive(new state))
        //Enregistrer le min pour cet état dans le cache
        //Retourner le min

        //Pour chaque entité
        long min = Integer.MAX_VALUE;

        final List<Entity> inRoomEntities = entities.stream().filter(e -> !inHall.contains(e)).toList();

        for (Entity entity : inRoomEntities) {
            if(!canMoveFrom(entity, entities))
                continue;

            //  Récupérer les positions valides
            Set<Point> validPositions = computeValidPositions(entity, entities);

            //    Pour chaque position valide
            for (Point validPosition : validPositions) {
                final Entity updatedEntity = Entity.e(validPosition, entity.type());
                //      Créer le nouvel état
                final Set<Entity> newState = entities.stream()
                        .filter(e -> e != entity)
                        .collect(Collectors.toSet());
                newState.add(updatedEntity);
                //        recursive(nouvel état)
                final long cost1 = cost(entity, validPosition);

                min = Math.min(min, cost1 + recursive(newState));

            }
        }
        CACHE.put(entities, min);
        return min;
    }

    private static boolean canMoveFrom(final Entity entity, final Set<Entity> entities) {
        final Room room = ROOMS_BY_POS.get(entity.position());
        if(!room.type().equals(entity.type()))
            return true;

        final int x = entity.position.x();
        return entities.stream().filter(e -> e.position.x() == x)
                .anyMatch(e -> !Objects.equals(e.type(), entity.type));
    }

    private static Point findAvailablePositionInRoom(final Entity entity, final Set<Entity> entities) {

        final List<Point> rooms = ROOMS_BY_TYPE.get(entity.type);
        final List<Entity> entitiesInRoom = entities.stream().filter(e -> rooms.contains(e.position())).toList();

        final boolean otherType = entitiesInRoom.stream().anyMatch(e -> !Objects.equals(e.type(), entity.type()));
        if (otherType)
            return null;

        final int firstAvailableIndex = entitiesInRoom.stream().mapToInt(e -> e.position().x()).min().orElse(4);
        final int y = rooms.get(0).y();
        final Point targetPos = p(firstAvailableIndex, y);

        if(canMoveTo(targetPos, entity, entities.stream().map(Entity::position).collect(Collectors.toSet())))
            return targetPos;

        return null;
    }

    private static List<Entity> inHallEntities(final Set<Entity> entities) {
        return entities.stream().filter(e -> e.position.y() == 0).toList();
    }

    private static long cost(final Entity entity, final Point validPosition) {
        return validPosition.distanceTo(entity.position) * ENTITY_MOVE_POINT.get(entity.type());
    }

    private static boolean canMoveTo(Point target, Entity moved, Set<Point> occupied) {
        final Point currentPos = moved.position;

        final int minX = Math.min(target.x(), currentPos.x());
        final int maxX = Math.max(target.x(), currentPos.x());
        for (int x = minX; x <= maxX; x++) {
            final Point p = p(x, 0);
            if (!p.equals(moved.position()) && occupied.contains(p))
                return false;
        }

        final int minY = Math.min(target.y(), currentPos.y());
        final int maxY = Math.max(target.y(), currentPos.y());

        for (int i = minY; i <= maxY; i++) {
            final Point p = p(currentPos.x(), i);
            if (!p.equals(moved.position()) && occupied.contains(p))
                return false;
        }
        return true;
    }

    private static Set<Point> computeValidPositions(final Entity entity, final Set<Entity> entities) {
        //NE s'arrête jamais sur les salles devant les pièces (pièces marquées par #)

        final Room room = ROOMS_BY_POS.get(entity.position);
        final Set<Point> result = new HashSet<>();
        final boolean inHall = room.type.equals(".");

        final Set<Point> entitiesPositions = entities.stream()
                .map(Entity::position)
                .collect(Collectors.toSet());

        //Si l'entité est dans le hall, elle ne va dans une pièce que si c'est la sienne
        Set<Point> validHomePositions = computeValidHomePositions(entity, entities);
        if (inHall) {
            //Ne va pas que dans sa pièce de destination si il n'y a pas d'autre entités de type différent
            result.addAll(validHomePositions);
        } else {
            //Je ne suis pas dans le hall, donc dans une pièce qui n'est pas la mienne je peux aller dans le hall ou
            //un piece disponible qui est la mienne
            Set<Point> validHallPositions = computeValidHallPositions(entity, entitiesPositions);
            result.addAll(validHallPositions);
            result.addAll(validHomePositions);
        }

        return result.stream()
                .filter(p -> canMoveTo(p, entity, entitiesPositions))
                .collect(Collectors.toSet());
    }

    private static Set<Point> computeValidHallPositions(final Entity entity, final Set<Point> entitiesPositions) {
        return ROOMS.stream()
                .filter(r -> r.type.equals("."))//Hall position
                .map(Room::position)
                .filter(p -> !entitiesPositions.contains(p))//Non déjà occupé
                .collect(Collectors.toSet());
    }

    private static Set<Point> computeValidHomePositions(final Entity entity,
                                                        final Set<Entity> entities) {
        final List<Point> homes = ROOMS_BY_TYPE.get(entity.type());

        final Set<Point> result = new HashSet<>(homes);
        for (Point home : homes) {
            for (Entity e : entities) {
                if (!e.position.equals(home)) {
                    continue;
                }
                if (e.type.equals(entity.type)) {
                    result.remove(home);
                } else {
                    return Collections.emptySet();
                }
            }
        }
        return result;
    }

    private static boolean isFinal(final Set<Entity> entities) {
        return FINAL_STATE.equals(entities);
    }

    record Room(Point position, String type) {

        static Room room(Point position, String type) {
            return new Room(position, type);
        }
    }

    record Entity(Point position, String type) {

        static Entity e(final Point position, final String type) {
            return new Entity(position, type);
        }
    }

    static String toString(final Collection<?> collection, final String delimiter) {
        return collection.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    static String toString(final Map<?, ?> collection, final String delimiter) {
        return collection.entrySet().stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    public static final String EXAMPLE = """
            #############
            #...........#
            ###B#C#B#D###
              #A#D#C#A#
              #########""";
}
